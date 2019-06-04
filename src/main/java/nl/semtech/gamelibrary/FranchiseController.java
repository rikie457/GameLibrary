package nl.semtech.gamelibrary;


import nl.semtech.gamelibrary.model.Franchise;
import nl.semtech.gamelibrary.model.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FranchiseController {


    @GetMapping("/franchise/add")
    public String sendFranchiseForm(Franchise franchise, Model model) {
        model.addAttribute("genres", GamelibraryApplication.genres);
        return "franchise/newfranchise";
    }

    @PostMapping("/franchise/add")
    public String processFranchiseForm(@Valid Franchise franchise, BindingResult bindingResult, @RequestParam("genre") int id) {
        if (bindingResult.hasErrors()) {
            return "franchise/newfranchise";
        }

        Genre genre = GamelibraryApplication.findGenreById(id);
        franchise.setGenre(genre);
        genre.addFranchiseToGenre(franchise);
        GamelibraryApplication.franchises.add(franchise);
        franchise.setId(GamelibraryApplication.franchises.size());
        return "redirect:/franchises";
    }

    @GetMapping("/franchises")
    public String showfranchises(Model model) {
        model.addAttribute("franchises", GamelibraryApplication.franchises);
        return "showfranchises";
    }

}
