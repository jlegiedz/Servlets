package servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// zadania do strony glownej bedzie obslugiwal ten servlet: odpowiednik RequestMapping w spring-boot
//@WebServlet("/")

@WebServlet(value = "/servlet", initParams = {@WebInitParam(name="APPLICATION_NAME", value = "")})
public class HelloServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Inicjujemy servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //chcemy by out sie wywolywal tylko wtedy gdy param value=true czyli zwroci stringa z web.xml
        //pobieramy parametr z konfiguracji
        String logRequestParam = getInitParameter("LOG-REQUEST");
        if("true".equalsIgnoreCase(logRequestParam)) {
            System.out.println("Wpadlo zadanie na ten adres.");
        }

        // PW sluzy do wyrzucenie tej wartosci ktora chcemy wyswietlic uzytkownikowi;
        // mamy tylko dostep do obiekotw request i response, nei ma metod jak w Spring
        // dlatego piszemy recznie wszystko
        PrintWriter out = resp.getWriter();
        //pobieramy drugi param konfiguracyjny
        String applicationName = getInitParameter("APPLICATION_NAME");
        out.println("HELLO SERVLET NA ADRESIE: <br/>" + applicationName);

        String adres = req.getRequestURI();
        out.println("<h1>Hello servlet pod adresem :</h1>" + adres);
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Niszczymy servlet");
    }
}
