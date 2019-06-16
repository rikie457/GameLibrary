package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @GetMapping("/login")
    public String getLogin(HttpSession session, Model model) {
        System.out.println(session.getAttribute("userid"));
        model.addAttribute("session", session.getAttribute("userid"));
        return "user/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        if (!GamelibraryApplication.checkUsernameAndPassword(username, password)) {
            model.addAttribute("error", "Username and/or Password are incorrect");
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


