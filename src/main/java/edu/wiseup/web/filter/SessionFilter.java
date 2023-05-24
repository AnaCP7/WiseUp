package edu.wiseup.web.filter;

import edu.wiseup.web.servlet.dto.User;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * El filtro SessionFilter se aplica a las páginas dentro de "/quiz/*" y "/profile-page/*" para asegurarse de que
 * solo se puedan acceder a ellas si hay una sesión de usuario activa. Si no hay una sesión de usuario activa,
 * el filtro redirige al usuario a la página de inicio de sesión "logIn.jsp".
 */
@WebFilter(urlPatterns = {"/quiz/*", "/profile-page/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class SessionFilter implements Filter {

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

        if (user == null) {
            // No hay una sesión de usuario activa, redirigir al usuario a la página de inicio de sesión "logIn.jsp"
            resp.sendRedirect("/WiseUp/login/login-form/logIn.jsp");
        } else {
            // Hay una sesión de usuario activa, pasar la solicitud y respuesta filtradas a la siguiente etapa del filtro
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
