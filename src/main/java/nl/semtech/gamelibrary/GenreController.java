package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Game;
import nl.semtech.gamelibrary.model.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        GamelibraryApplication.genres.add(genre);
        genre.setId(GamelibraryApplication.genres.size());
        return "redirect:/genres";
    }

    @GetMapping("/genre/edit/{id}")
    public String sendGenreEditForm(Model model, @PathVariable("id") int id) {
        Game genre = GamelibraryApplication.findGameById(id);
        model.addAttribute("genre", genre);
        return "genre/editgenre";
    }

    @PostMapping("/genre/edit/{id}")
    public String processGenreEditForm(@PathVariable("id") int id, @Valid @ModelAttribute Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genre/editgenre";
        }
        GamelibraryApplication.updateGenre(id, genre);
        return "redirect:/genres";
    }


    @GetMapping("genre/delete/{id}")
    public String deleteGenre(@PathVariable("id") int id) {
        if (id != 1) {
            GamelibraryApplication.deleteGenre(id);
        } else {
            return "genre/deletegenre";
        }
        return "redirect:/genres";
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
