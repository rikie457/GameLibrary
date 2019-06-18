package nl.semtech.gamelibrary;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Utility {
    static Cookie searchCookie(HttpServletRequest request, String cookiename){
        Cookie[] cookies = request.getCookies();
        Cookie cookie, findcookie;
        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            if (cookie.getName().equals(cookiename)) {
                findcookie = cookies[i];
                return findcookie;
            }
        }
        return null;
    }


    static String getCookieValue(Cookie cookie){
        String value = "Geen data gevonden";
        if(cookie == null){
            return value;
        }
        value = cookie.getValue();
        return value;
    }
}
