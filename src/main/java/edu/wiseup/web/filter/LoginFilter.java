package edu.wiseup.web.filter;

import edu.wiseup.web.servlet.dto.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * El filtro SessionFilter se aplica a la página "/login/login.jsp" para asegurarse de que se evite
 * acceder a ella si hay una sesión de usuario activa. Si no hay una sesión de usuario activa,
 * el filtro deja continuar a la página de inicio de sesión "login.jsp".
 */
@WebFilter(urlPatterns = {"/login/login.jsp"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class LoginFilter implements Filter {

    /**
     * Inicializa el filtro.
     *
     * @param filterConfig La configuración del filtro.
     * @throws ServletException Si ocurre un error durante la inicialización del filtro.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No se requiere ninguna inicialización adicional en este caso
    }

    /**
     * Realiza el filtrado de la solicitud y la respuesta.
     *
     * @param servletRequest  La solicitud entrante.
     * @param servletResponse La respuesta saliente.
     * @param filterChain     La cadena de filtros a la cual pasar la solicitud y respuesta filtradas.
     * @throws IOException      Si ocurre un error de E/S durante el filtrado.
     * @throws ServletException Si ocurre un error durante el filtrado.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("userSession");

        if (user != null) {
            // Hay una sesión de usuario activa, redirigir al usuario a la página de inicio "/home"
            resp.sendRedirect("/WiseUp/home");
        } else {
            // No hay una sesión de usuario activa, pasar la solicitud y respuesta filtradas a la siguiente etapa del filtro
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Limpia y libera los recursos utilizados por el filtro.
     */
    @Override
    public void destroy() {
        // No se requiere ninguna limpieza o liberación de recursos en este caso
    }
}
