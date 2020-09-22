package com.zz.Filter;


import com.zz.model.User;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/9/8 15:06
 */
@WebFilter
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        StringBuffer server = request.getRequestURL();

        // 获得用户请求的URI
        String path = request.getRequestURI();

        User user = (User) session.getAttribute("user");

        // 登陆页面无需过滤
        if (path.indexOf("/login") > -1 || path.indexOf("/register.do") > -1|| path.indexOf("/forget.do") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(server.toString().contains(".css") || server.toString().contains(".js") || server.toString().contains(".png")|| server.toString().contains(".jpg")|| server.toString().contains("favicon.ico")|| server.toString().contains(".woff2")){
            //如果发现是css或者js文件，直接放行
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (user == null  && !(path.indexOf("/login.json") > -1)) {
            // 跳转到登陆页面
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
