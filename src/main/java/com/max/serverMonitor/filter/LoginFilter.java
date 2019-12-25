package com.max.serverMonitor.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter", urlPatterns = "*.jsp")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        Object obj = session.getAttribute("user");

        // 获取请求的URL
        String url = servletRequest.getRequestURI();
        if (url.indexOf("/login") > -1){
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        else if (obj == null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        else{
            chain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
