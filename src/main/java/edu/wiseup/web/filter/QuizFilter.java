package edu.wiseup.web.filter;

import edu.wiseup.persistence.dao.Question;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * El filtro QuizFilter se aplica a las páginas "quiz.jsp" y "score.jsp" para asegurarse de que solo se puedan acceder
 * a ellas si existe una lista de preguntas en la sesión del usuario. Si no hay preguntas en la sesión, el filtro
 * redirige al usuario a la página "quiz-start.jsp".
 */
@WebFilter(urlPatterns = {"/quiz/quiz.jsp", "/quiz/score.jsp"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class QuizFilter implements Filter {

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

        ArrayList<Question> questions = (ArrayList<Question>) req.getSession().getAttribute("questions");

        if (questions == null) {
            // No hay preguntas en la sesión, redirigir al usuario a la página "quiz-start.jsp"
            resp.sendRedirect("/WiseUp/quiz/quiz-start.jsp");
        } else {
            // Hay preguntas en la sesión, pasar la solicitud y respuesta filtradas a la siguiente etapa del filtro
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
