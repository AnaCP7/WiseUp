import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletVolverLogin", urlPatterns = {"/servlet-volver-login"})
public class LoginResponse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User usuario = (User) req.getSession().getAttribute("usuarioSesion");
        if (usuario != null) {
            resp.sendRedirect("/AplicativoWeb/servlet-login");
        } else {
            resp.sendRedirect("/AplicativoWeb/login/login.jsp");
        }
    }
}