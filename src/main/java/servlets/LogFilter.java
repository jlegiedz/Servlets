package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//zapisanie kazdego loga do pliku
@WebFilter(servletNames = {"InfoServlet"})
public class LogFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(LogFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.log(Level.INFO, "Utworzenie instancji filtra");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String url = httpRequest.getRequestURL().toString();
            String queryParams = httpRequest.getQueryString();
            LOGGER.log(Level.INFO, String.format("Wywolano adres url  %s z parametrem : %s", url, queryParams));
        }

        //po tym jak filtr zwaliduje zadanie:
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, "Usuwanie instancji Filtra");
    }
}
