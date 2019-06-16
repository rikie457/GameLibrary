package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Franchise;
import nl.semtech.gamelibrary.model.Game;
import nl.semtech.gamelibrary.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class GameController {

    @GetMapping("/games")
    public String showGames(Model model, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        model.addAttribute("games", user.getGames());
        return "game/showgames";
    }

    @GetMapping("/game/add")
    public String sendGameForm(Game game, Model model, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        model.addAttribute("franchises", user.getFranchises());
        return "game/newgame";
    }

    @PostMapping("/game/add")
    public String processGameForm(@Valid Game game, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
            model.addAttribute("franchises", user.getFranchises());
            return "game/newgame";
        }
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        Franchise franchise = user.findFranchiseById(game.getFranchiseId());
        game.setFranchise(franchise);
        franchise.addGameToFranchise(game);
        user.getGames().add(game);
        game.setId(user.getGames().size());
        return "redirect:/games";
    }


    @GetMapping("/game/edit/{id}")
    public String sendGameEditForm(Model model, @PathVariable("id") int id, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        Game game = user.findGameById(id);
        model.addAttribute("game", game);
        model.addAttribute("franchises", user.getFranchises());
        return "game/editgame";
    }

    @PostMapping("/game/edit/{id}")
    public String processGameEditForm(@PathVariable("id") int id, @Valid @ModelAttribute Game game, BindingResult bindingResult, @RequestParam("oldfranchiseid") int oldfranchiseid, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
            model.addAttribute("franchises", user.getFranchises());
            return "game/editgame";
        }
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        user.updateGame(id, game, oldfranchiseid);

        return "redirect:/games";
    }

    @GetMapping("game/delete/{id}")
    public String deleteGame(@PathVariable("id") int id, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        user.deleteGame(id);

        return "redirect:/games";
    }

    @GetMapping("game")
    public String showGame(Model model, @RequestParam String name, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        Game game = user.findGameByName(name);
        if (game == null) {
            return "redirect:/games";
        }
        model.addAttribute("game", game);
        return "game/showgame";
    }
}
