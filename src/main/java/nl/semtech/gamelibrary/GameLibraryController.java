package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class GameLibraryController {
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        return "general/index";
    }

    @PostMapping("/")
    public String processSearch(HttpSession session, Model model, @RequestParam("query") String query) {
        if (query != null) {
            User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
            ArrayList<Object> results = user.searchAll(query);
            model.addAttribute("results", results);
            model.addAttribute("query", query);
        }
        return "general/index";
    }
}
