package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class GameLibraryController {
    @GetMapping("/")

    public String index(HttpServletRequest request, Model model, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie;
        String value = null;
        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            if (cookie.getName().equals("lastlogin")) {
                value = cookie.getValue();
            }
        }
        if (value == null) {
            value = "No last visit";
        }
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        model.addAttribute("nameuser", user.getName());
        model.addAttribute("lastlogin", value);

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
