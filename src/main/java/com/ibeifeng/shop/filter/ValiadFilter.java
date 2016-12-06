package com.ibeifeng.shop.filter;

import com.ibeifeng.shop.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ValiadFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入过滤页面");
        RequestDispatcher dispatcher=request.getRequestDispatcher("/page/index.jsp");
        HttpServletRequest request1=(HttpServletRequest)request;
        HttpServletResponse response1=(HttpServletResponse)response;
        HttpSession session=request1.getSession(true);
        User user=(User) session.getAttribute("LoginUser");
        if (user==null||user.getStatus()==0){
            dispatcher.forward(request,response);
        }

        chain.doFilter(request,response);

    }

    public void destroy() {

    }
}
