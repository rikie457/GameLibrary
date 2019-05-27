package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FranchiseController {

    @GetMapping("/genre")
    public String showFranchises(Model model, @RequestParam String name){
        Genre genre = GamelibraryApplication.findGenreByName(name);
        model.addAttribute("franchises", genre.getFranchises());
        return "showfranchises";
    }
}
