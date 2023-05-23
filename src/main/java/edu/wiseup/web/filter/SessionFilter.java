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

@WebFilter(urlPatterns={"/quiz/*", "/profile-page/*"}, dispatcherTypes={DispatcherType.REQUEST,DispatcherType.FORWARD})
public class SessionFilter implements  Filter {

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

        User user = (User)req.getSession().getAttribute("userSession");

        if(user == null){
            resp.sendRedirect("/WiseUp/login/login-form/logIn.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
