package edu.wiseup.web.filter;

import edu.wiseup.persistence.dao.Question;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns={"/quiz/quiz.jsp"}, dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD})
public class QuizFilter implements  Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws
            IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        ArrayList<Question> questions = (ArrayList<Question>) req.getSession().getAttribute("questions");
        System.out.println(questions);

        if(questions == null){
            resp.sendRedirect("/WiseUp/quiz/quiz-start.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
