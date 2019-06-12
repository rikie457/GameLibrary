package nl.semtech.gamelibrary;


import nl.semtech.gamelibrary.model.Franchise;
import nl.semtech.gamelibrary.model.Genre;
import nl.semtech.gamelibrary.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.ui.Model;

@Controller
public class FranchiseController {


    @GetMapping("/franchise/add")
    public String sendFranchiseForm(Franchise franchise, Model model, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        model.addAttribute("genres", user.getGenres());
        return "franchise/newfranchise";
    }

    @PostMapping("/franchise/add")
    public String processFranchiseForm(@Valid Franchise franchise, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
            model.addAttribute("genres", user.getGenres());
            return "franchise/newfranchise";
        }
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        Genre genre = user.findGenreById(franchise.getGenreid());
        franchise.setGenre(genre);
        genre.addFranchiseToGenre(franchise);
        user.getFranchises().add(franchise);
        franchise.setId(user.getFranchises().size());
        return "redirect:/franchises";
    }

    @GetMapping("/franchises")
    public String showFranchises(Model model, HttpSession session) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        model.addAttribute("franchises", user.getFranchises());
        return "franchise/showfranchises";
    }

//    @GetMapping("/franchise")
//    public String showFranchise(Model model, @RequestParam String name){
//        Franchise franchise = GamelibraryApplication.findFranchiseByName(name);
//        if (franchise == null){
//            return "redirect:/franchises";
//        }
//        model.addAttribute("franchise", franchise);
//        return "franchise/showfranchise";
//
//    }
//
//    @GetMapping("/franchise/edit/{id}")
//    public String sendFranchiseEditForm(Model model, @PathVariable("id") int id) {
//        Franchise franchise = GamelibraryApplication.findFranchiseById(id);
//        model.addAttribute("franchise", franchise);
//        model.addAttribute("genres", GamelibraryApplication.genres);
//        return "franchise/editfranchise";
//    }
//
//    @PostMapping("/franchise/edit/{id}")
//    public String processFranchiseEditForm(@PathVariable("id") int id, @Valid @ModelAttribute Franchise franchise, BindingResult bindingResult, @RequestParam("oldgenreid") int oldgenreid, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("genres", GamelibraryApplication.genres);
//            return "franchise/editfranchise";
//        }
//        GamelibraryApplication.updateFranchise(id, franchise, oldgenreid);
//
//        return "redirect:/franchises";
//    }
//
//    @GetMapping("franchise/delete/{id}")
//    public String deleteFranchise(@PathVariable("id") int id) {
//        if(id != 1) {
//            GamelibraryApplication.deleteFranchise(id);
//        }else{
//            return "franchise/deletefranchise";
//        }
//
//
//        return "redirect:/franchises";
//    }
//

}
