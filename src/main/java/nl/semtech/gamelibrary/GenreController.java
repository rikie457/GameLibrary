package nl.semtech.gamelibrary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenreController {

    @GetMapping("/genres")
    public String showGenres(Model model){
        model.addAttribute("genres", GamelibraryApplication.genres);
        return "showgenres";
    }
}
