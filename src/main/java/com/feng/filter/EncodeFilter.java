package com.feng.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "EncodeFilter")
public class EncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(req, resp);

        System.out.println("EncodeFilter.........................");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //将字符编码集设置成utf-8
        request.setCharacterEncoding("UTF-8");  //只针对于post方式
        //响应回去的字符编码集
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        chain.doFilter(request,response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
