package edu.wiseup.web.servlet;

import edu.wiseup.web.servlet.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/check-login-servlet"})
public class CheckLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("userSession");
        if(user != null){
            resp.sendRedirect("/WiseUp/login/login-done.jsp");
        }
        else {
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/WiseUp/login/login-form/logIn.html");
    }
}