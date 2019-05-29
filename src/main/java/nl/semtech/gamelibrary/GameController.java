package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Franchise;
import nl.semtech.gamelibrary.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String processGameForm(@Valid Game game, BindingResult bindingResult, @RequestParam("franchise") int id) {
        if (bindingResult.hasErrors()) {
            return "game/newgame";
        }
        game.setId(GamelibraryApplication.games.size() + 1);
        Franchise franchise = GamelibraryApplication.findFranchiseById(id);
        game.setFranchise(franchise);
        franchise.addGameToFranchise(game);
        GamelibraryApplication.games.add(game);
        return "redirect:/games";
    }

    @GetMapping("/game/edit")
    public String sendGameEditForm(Model model, @RequestParam("id") int id) {
        Game game = GamelibraryApplication.findGameById(id);
        model.addAttribute("game", game);
        model.addAttribute("editfranchise", GamelibraryApplication.findFranchiseById(game.getFranchise().getId()));
        model.addAttribute("franchises", GamelibraryApplication.franchises);
        return "game/editgame";
    }

    @PostMapping("/game/edit")
    public String processGameEditForm(@Valid Game game, BindingResult bindingResult, @RequestParam("franchise") int id) {
        if (bindingResult.hasErrors()) {
            return "game/editgame";
        }

        return "redirect:/games";
    }

}
