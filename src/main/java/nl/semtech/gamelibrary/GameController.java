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
    public String processGameForm(@Valid Game game, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("franchises", GamelibraryApplication.franchises);
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
        model.addAttribute("franchises", GamelibraryApplication.franchises);
        return "game/editgame";
    }

    @PostMapping("/game/edit/{id}")
    public String processGameEditForm(@PathVariable("id") int id, @Valid @ModelAttribute Game game, BindingResult bindingResult, @RequestParam("oldfranchiseid") int oldfranchiseid, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("franchises", GamelibraryApplication.franchises);
            return "game/editgame";
        }
        GamelibraryApplication.updateGame(id, game, oldfranchiseid);

        return "redirect:/games";
    }

    @GetMapping("game/delete/{id}")
    public String deleteGame(@PathVariable("id") int id) {
        GamelibraryApplication.deleteGame(id);

        return "redirect:/games";
    }
  
    @GetMapping("game")
    public String showGame(Model model, @RequestParam String name){
        Game game = GamelibraryApplication.findGameByName(name);
        if (game == null){
            return "redirect:/games";
        }
        model.addAttribute("game", game);
        return "game/showgame";
    }
}
