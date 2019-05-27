package nl.semtech.gamelibrary;


import nl.semtech.gamelibrary.model.Franchise;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import nl.semtech.gamelibrary.model.Genre;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FranchiseController {


    @GetMapping("/franchise/add")
    public String sendFranchiseForm(Franchise franchise){
        return "franchise/newfranchise";
    }

    @PostMapping("/franchise/add")
    public String processFranchiseForm(@Valid Franchise franchise, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "franchise/newfranchise";
        }
        return "redirect:/franchises";
    }
    @GetMapping("/genre")
    public String showFranchises(Model model, @RequestParam String name){
        Genre genre = GamelibraryApplication.findGenreByName(name);
        model.addAttribute("franchises", genre.getFranchises());
        return "showfranchises";
    }
}
