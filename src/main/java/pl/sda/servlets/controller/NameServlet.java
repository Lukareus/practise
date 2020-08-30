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
    public static final String FEMALE_NAME_REGEX = ".+a";
    public static final String FEMALE_PATH = "/female";
    public static final String MALE_PATH = "/male";
    public static final String MAIN_SITE_PATH = "/index.jsp";

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
        String path = giveName
                .map(name -> convertNameToProperPath(name, writer))
                .orElseGet(() -> getPathWhenMissingName(writer));
        req.getRequestDispatcher(path).include(req, resp);
    }

    private String convertNameToProperPath(String name, PrintWriter writer){

            if (name.matches(NAME_REGEX)) {
                writer.println("<h4>Twoje imię to " + name + "</h4>");
                return name.matches(FEMALE_NAME_REGEX) ? FEMALE_PATH : MALE_PATH;
            } else {
                writer.println("<h4>Imie podane w złym formacie</h4>");
                return MAIN_SITE_PATH;
            }
    }

    private String getPathWhenMissingName(PrintWriter writer){
        writer.println("<h4>Nie podałeś imienia</h4>");
        return "/index.jsp";
        }
    }
