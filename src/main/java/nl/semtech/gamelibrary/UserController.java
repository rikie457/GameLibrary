package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class UserController {
    @GetMapping("/login")
    public String getLogin(HttpSession session, Model model) {
        if(session.getAttribute("userid") != null){
            return "redirect:/";
        }
        model.addAttribute("authenticated", false);
        return "user/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        if (!GamelibraryApplication.checkUsernameAndPassword(username, password)) {
            model.addAttribute("error", "Username and/or Password are incorrect");
            model.addAttribute("authenticated", false);
            return "user/login";
        }
        if (GamelibraryApplication.getUserByUsernameAndPassword(username, password) != null) {
            User user = GamelibraryApplication.getUserByUsernameAndPassword(username, password);
            session.setAttribute("userid", user.getId());
            return "redirect:/";
        }
        return "user/login";
    }


    @GetMapping("/logout")
    public String processLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}


