package edu.wiseup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletLogin", urlPatterns = {"/servlet-login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        User user = (User) req.getSession().getAttribute("userSession");

        if(user != null){
            resp.sendRedirect("/WiseUp/login/login-done.jsp");
        }
        else {
            //TODO Comprobar si existe ese usuario
            //String userSet = ...
            //String passSet = ...

            String userEntered = req.getParameter("user");
            String passEntered = req.getParameter("pass");

            if ((userEntered != null) && (passEntered != null)
                // && passEntered.equals(passSet) && userEntered.equals(userSet)
            ) {

                user = User.builder().username(userEntered).password(passEntered).build();
                //TODO Meterlo en la BBDD

                req.getSession().setMaxInactiveInterval(Integer.parseInt(getServletContext().getInitParameter("sessionTimeout")));
                req.getSession().setAttribute("userSession", user);

                resp.sendRedirect("/WiseUp/login/sign-up-done.jsp");
            } else {
                resp.sendRedirect("/WiseUp/login/login.jsp");
            }
        }
    }
}