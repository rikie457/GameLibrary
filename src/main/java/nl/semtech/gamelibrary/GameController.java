package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Franchise;
import nl.semtech.gamelibrary.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class GameController {

    @GetMapping("/games")
    public String showGames(Model model) {
        model.addAttribute("games", GamelibraryApplication.games);
        return "game/showgames";
    }

    @GetMapping("/game/add")
    public String sendGameForm(Game game, Model model) {
        model.addAttribute("franchises", GamelibraryApplication.franchises);
        return "game/newgame";
    }

    @PostMapping("/game/add")
    public String processGameForm(@Valid Game game, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "game/newgame";
        }
        Franchise franchise = GamelibraryApplication.findFranchiseById(game.getFranchiseId());
        game.setFranchise(franchise);
        franchise.addGameToFranchise(game);
        GamelibraryApplication.games.add(game);
        game.setId(GamelibraryApplication.games.size());
        return "redirect:/games";
    }

    @GetMapping("/game/edit/{id}")
    public String sendGameEditForm(Model model, @PathVariable("id") int id) {
        Game game = GamelibraryApplication.findGameById(id);
        model.addAttribute("game", game);
        System.out.println(game.toString());
        model.addAttribute("franchises", GamelibraryApplication.franchises);
        return "game/editgame";
    }

    @PutMapping("/game/edit/")
    public String processGameEditForm(@Valid Game game, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "game/editgame";
        }
        System.out.println(game.getFranchise());
//        Franchise franchise = GamelibraryApplication.findFranchiseById(game.getFranchiseId());
//        if(game.getFranchise() != franchise){
//            Franchise oldFranchise = game.getFranchise();
//            oldFranchise.deleteGameFromFranchise(game);
//            game.setFranchise(franchise);
//            franchise.addGameToFranchise(game);
//        }

        return "redirect:/games";
    }

}
