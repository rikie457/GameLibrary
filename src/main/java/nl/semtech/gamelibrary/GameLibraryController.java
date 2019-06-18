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
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        Cookie cookie = Utility.searchCookie(request,  Integer.toString(user.getId()));
        String value = Utility.getCookieValue(cookie);
        model.addAttribute("nameuser", user.getName());
        model.addAttribute("lastlogin", value);

        return "general/index";
    }

    @PostMapping("/")
    public String processSearch(HttpServletRequest request, HttpSession session, Model model, @RequestParam("query") String query) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        Cookie cookie = Utility.searchCookie(request,  Integer.toString(user.getId()));
        String value = Utility.getCookieValue(cookie);
        model.addAttribute("nameuser", user.getName());
        model.addAttribute("lastlogin", value);
        if (query != null) {
            ArrayList<Object> results = user.searchAll(query);
            model.addAttribute("results", results);
            model.addAttribute("query", query);
        }
        return "general/index";
    }
}
