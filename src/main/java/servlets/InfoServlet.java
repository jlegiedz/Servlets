package servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/info")
public class InfoServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.println(req.getRequestURL());
        String param = req.getParameter("param");
        // zwaraca to co po ? klucze i wartosci

        Map<String,String[]> paramMap =req.getParameterMap();
       for(Map.Entry entry: paramMap.entrySet()){
           String key = (String) entry.getKey();
           String[] values =  paramMap.get(key);
           for (int i = 0; i <values.length ; i++) {
               out.println("Klucz: " + key + " i wartosc: " + values[i]);
           }
       }

        out.println("Parameters: " + param);
        LocalDateTime date = LocalDateTime.now();
        out.println("Dziesiejsza data: " + date);


        //wyswietlic wszystkie atrybuty sesji:
        //doGet zapisuje date ostatniego zadania i adres url


        HttpSession session = req.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String key = attributeNames.nextElement();
            out.println("Klucz: " + key + ", Wartosc: "+ session.getAttribute(key));
        }

        String dateString = date.toString();
        //storing values in session:
        session.setAttribute("LastRequestedDate", dateString);
        session.setAttribute("LastURL", req.getRequestURL());


        out.println("ciasteczka: ");
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie: cookies){
            out.println("Klucz: "+ cookie.getName()+ " wartosc: "+ cookie.getValue());
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
