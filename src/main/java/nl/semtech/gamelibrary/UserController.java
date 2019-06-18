package nl.semtech.gamelibrary;

import nl.semtech.gamelibrary.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class UserController {
    @GetMapping("/login")
    public String getLogin(HttpSession session, Model model) {
        if (session.getAttribute("userid") != null) {
            return "redirect:/";
        }
        model.addAttribute("authenticated", false);
        return "user/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session, HttpServletResponse response) {
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


    @GetMapping("/register")
    public String getRegister(User user, Model model, HttpSession session) {
        if (session.getAttribute("userid") != null) {
            return "redirect:/";
        }
        model.addAttribute("authenticated", false);
        return "user/register";
    }


    @PostMapping("/register")
    public String processRegister(@Valid User newuser, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "user/register";
        }
        GamelibraryApplication.users.add(newuser);
        newuser.setId(GamelibraryApplication.users.size());
        GamelibraryApplication.addDefaultToUser(newuser);
        if (GamelibraryApplication.getUserByUsernameAndPassword(newuser.getUsername(), newuser.getPassword()) != null) {
            User user = GamelibraryApplication.getUserByUsernameAndPassword(newuser.getUsername(), newuser.getPassword());
            session.setAttribute("userid", user.getId());
            return "redirect:/";
        }

        return "user/register";
    }


    @GetMapping("/logout")
    public String processLogout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        User user = GamelibraryApplication.getUserById((int) session.getAttribute("userid"));
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        Cookie oldcookie = null, cookie;

        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            if (cookie.getName().equals(Integer.toString(user.getId()))) {
                oldcookie = cookie;
            }
        }
        if (oldcookie == null) {
            cookie = new Cookie(Integer.toString(user.getId()), new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Date()));
            cookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookie);
        } else {
            oldcookie.setValue(new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Date()));
            response.addCookie(oldcookie);
        }

        return "redirect:/login";
    }
}


