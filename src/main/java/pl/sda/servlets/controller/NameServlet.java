package pl.sda.servlets.controller;

import pl.sda.servlets.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "nameServlet", urlPatterns = {"/name"})
public class NameServlet extends HttpServlet {

    public static final String NAME_REGEX = "[A-Z][a-z]+";
    public static final String PARAMETER_NAME = "name";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //przekazać zadanie do pliku jsp
        ResponseUtil.setWriter(resp);
        resp.getWriter().println("<h1 style= \"color: red\">Metoda get dla /name nie istnieje</h1>");
        req.getRequestDispatcher("/index.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ResponseUtil.setWriter(resp);
        PrintWriter writer = resp.getWriter();

        Optional<String> giveName = Optional.ofNullable(req.getParameter(PARAMETER_NAME));
        Runnable handleMissingName = () -> writer.println("<h4>Nie podałeś imienia</h4>");
        giveName.ifPresentOrElse(name -> processNameParameter(name, writer), handleMissingName);

        //wyswietl imie uzytkownika
        // prześlij go na strone główną
        req.getRequestDispatcher("/index.jsp").include(req, resp);
    }

//    private Runnable handleMissingName(HttpServletResponse resp) {
//
//        return () -> {
//            try{
//                resp.getWriter().println("<h4>Nie podałeś imienia</h4>");
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        };
//    }


    private void processNameParameter(String name, PrintWriter writer){

        //czy imie jest puste
        //czy imie zawiera tylko litery
        //wyswietlic informacje
        try {
            if (name.matches(NAME_REGEX)) {
                writer.println("<h4>Twoje imię to" + name + "</h4>");
            } else {
                writer.println("<h4>Imie podane w złym formacie</h4>");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
