package mx.uv.fiee.iinf.paradigmas.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ("/formServlet")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var parameters = req.getParameterNames();

        while (parameters.hasMoreElements()) {
            System.out.println (parameters.nextElement());
        }

        var content = req.getParameter ("address");
        resp.getWriter().write(content);
    }

}
