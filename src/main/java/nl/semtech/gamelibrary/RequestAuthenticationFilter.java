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
        System.out.println(url);
        if (ALLOWED_URL_LIST.contains(url)) {
            filterChain.doFilter(request, response);
        } else if (session == null || session.getAttribute("userid") == null) {
            ((HttpServletResponse) response).sendRedirect("/login");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}