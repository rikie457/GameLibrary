package nl.semtech.gamelibrary;


import nl.semtech.gamelibrary.model.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GenreController {

    @GetMapping("/genre/add")
    public String sendGenreForm(Genre genre) {
        return "genre/newgenre";
    }

    @PostMapping("/genre/add")
    public String processGenreForm(@Valid Genre genre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genre/newgenre";
        }
        return "redirect:/genre";
    }

    @GetMapping("/genres")
    public String showGenres(Model model){
        model.addAttribute("genres", GamelibraryApplication.genres);
        return "genre/showgenres";
    }

    @GetMapping("/genre")
    public String showGenre(Model model, @RequestParam String name){
        Genre genre = GamelibraryApplication.findGenreByName(name);
        if (genre == null){
            return "redirect:/genres";
        }
        model.addAttribute("genre", genre);
        return "genre/showgenre";
    }
}
