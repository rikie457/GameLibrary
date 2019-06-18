package nl.semtech.gamelibrary;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RequestAuthenticationFilter implements Filter {

    //These are all the allowed URLS
    protected static final List<String> ALLOWED_URL_LIST = Arrays.asList("/login", "/error", "/register", "/style.css");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        String url = (request.getRequestURI());
        //Zit link die de gebruiker wil bezoeken in de toegestaande url lijst?
        if (ALLOWED_URL_LIST.contains(url)) {
            //Laat de gebruiker door.
            filterChain.doFilter(request, response);
            //Is de gebruiker  niet inlogd?
        } else if (session == null || session.getAttribute("userid") == null) {
            //Stuur de gebruiker door naar de login pagina.
            ((HttpServletResponse) response).sendRedirect("/login");
        } else {
            //Laat de gebruiker door.
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}