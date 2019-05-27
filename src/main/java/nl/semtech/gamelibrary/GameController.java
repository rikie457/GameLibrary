package nl.semtech.gamelibrary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/games")
    public String showGames(Model model){
        model.addAttribute("games", GamelibraryApplication.games);
        return "showgames";
    }
}
