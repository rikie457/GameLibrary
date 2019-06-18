package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.Genre;
import nl.semtech.gamelibrary.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class GenreController {

    @GetMapping("/genre/add")
    public String sendGenreForm(Genre genre, HttpSession session) {
        return "genre/newgenre";
    }

    @PostMapping("/genre/add")
    public String processGenreForm(@Valid Genre genre, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "genre/newgenre";
        }
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        user.getGenres().add(genre);
        genre.setId(user.getGenres().size());
        return "redirect:/genres";
    }

    @GetMapping("/genre/edit/{id}")
    public String sendGenreEditForm(Model model, @PathVariable("id") int id, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        Genre genre = user.findGenreById(id);
        model.addAttribute("genre", genre);
        return "genre/editgenre";
    }

    @PostMapping("/genre/edit/{id}")
    public String processGenreEditForm(@PathVariable("id") int id, @Valid @ModelAttribute Genre genre, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "genre/editgenre";
        }
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        user.updateGenre(id, genre);
        return "redirect:/genres";
    }


    @GetMapping("genre/delete/{id}")
    public String deleteGenre(@PathVariable("id") int id, HttpSession session) {
        if (id != 1) {
            User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
            user.deleteGenre(id);
        } else {
            return "genre/deletegenre";
        }
        return "redirect:/genres";
    }


    @GetMapping("/genres")
    public String showGenres(Model model, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        model.addAttribute("genres", user.getGenres());
        return "genre/showgenres";
    }

    @GetMapping("/genre")
    public String showGenre(Model model, @RequestParam String name, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        Genre genre = user.findGenreByName(name);
        if (genre == null) {
            return "redirect:/genres";
        }
        model.addAttribute("genre", genre);
        return "genre/showgenre";
    }
}
