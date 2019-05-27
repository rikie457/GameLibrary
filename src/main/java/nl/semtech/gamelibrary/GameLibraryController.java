package nl.semtech.gamelibrary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameLibraryController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
