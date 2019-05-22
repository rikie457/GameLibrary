package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class GameController {

    @GetMapping("/games")
    public String showGames(Model model){
        model.addAttribute("games", GamelibraryApplication.games);
        return "showgames";
    }

    @GetMapping("/game/add")
    public String sendGameForm(Game game){
        return "game/newgame";
    }

    @PostMapping("/game/add")
    public String processGameForm(Model model, @Valid Game game, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "game/newgame";
        }

        model.addAttribute("message", "Following game added");
        model.addAttribute("addedobject", game);
        return "showmessage";
    }

}
