package servlets;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//filtr odnajduje Servlet ktory bedzie validowal po nazwie podanej w adnotacji Servletu

@WebFilter(servletNames = {"LoginServlet"})
public class userDataFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String methodType = httpRequest.getMethod();
        if(methodType.equalsIgnoreCase("post")) {
            String login = httpRequest.getParameter("login");
            String password = httpRequest.getParameter("password");
            if ((login.equals("")|| !login.isEmpty()) || (password.equals("")|| !password.isEmpty())) {
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
               // httpResponse.sendRedirect("error.jsp");
               httpResponse.sendRedirect("login?error=incorrectData");
                return;
            }
        }
        //filtr zwaliduje dane ale by otrzymac odpowiedz:
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }


}
