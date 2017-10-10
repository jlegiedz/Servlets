package servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet(name ="LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //przekazanie zadania do strony jsp
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //nazwa parametru z formularza-odwzorowanie ModelAtrribute ze Spring-Boota
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //przekierowanie na strone glowna- w query string login uzytkownika zczytac go i dac info login uzytkwoniak
        HttpSession session = req.getSession();
        session.setAttribute("user", login);
        session.setAttribute("validTo", LocalDateTime.now().plusSeconds(90));
        resp.sendRedirect("main.jsp");
    }


}
