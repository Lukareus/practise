package pl.sda.servlets.controller;

import pl.sda.servlets.utils.ResponseUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/MaleServlet", urlPatterns = {"/male"})
public class MaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseUtil.setWriter(response);
        response.getWriter().println("<h4>Jesteś facetem!</h4>");
        response.getWriter().println("<a href= \"index.jsp\">Wróć do strony głównej</a>");

    }
}
