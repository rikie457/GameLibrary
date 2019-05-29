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

    @GetMapping("/franchises")
    public String showFranchises(Model model){
        model.addAttribute("franchises", GamelibraryApplication.franchises);
        return "franchise/showfranchises";
    }

    @GetMapping("/franchise")
    public String showFranchise(Model model, @RequestParam String name){
        Franchise franchise = GamelibraryApplication.findFranchiseByName(name);
        if (franchise == null){
            return "redirect:/franchises";
        }
        model.addAttribute("franchise", franchise);
        return "franchise/showfranchise";
    }
}
