package pl.sda.servlets.utils;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

    public static void setWriter(HttpServletResponse resp){
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
    }
}
