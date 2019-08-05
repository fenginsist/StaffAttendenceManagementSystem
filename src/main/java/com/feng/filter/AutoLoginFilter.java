package com.feng.filter;

import com.feng.bean.UserInfo;
import com.feng.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebFilter(filterName = "AutoLoginFilter")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(req, resp);

        /*
        * 自动登录
        * 从Cookie中取值：   取autoLogin，name,password
        * */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Cookie[] cookies = request.getCookies();
        String autoLogin = "";
        String name = "";
        String password = "";
        if (cookies!=null || cookies.length>0){     //如果不判断，当为空时，会报空指针异常
            for (Cookie cookie : cookies){
                if ("autoLogin".equals(cookie.getName())){
                    autoLogin = cookie.getValue();
                }
                if ("name".equals(cookie.getName())){       //取name的时候，记得要编码
                    name = URLEncoder.encode(cookie.getName(), "UTF-8");
                }
                if ("password".equals(cookie.getName())){
                    password = cookie.getValue();
                }
            }
        }

        //判断是否自动登录
        String autoLoginExit = request.getParameter("autoLogin");
        if (!("false".equals(autoLoginExit))) {
            if (autoLogin != null && autoLogin.length()>0 && !("".equals(autoLogin)) ) {
                //判断用户是否有用户名和密码
                if (name == null || password == null || "".equals(name) || "".equals(password)) {
                    chain.doFilter(request, response);
                } else {
                    //直接调用service 放行
                    UserService userService = new UserService();
                    UserInfo userInfo = userService.userLogin(name, password);
                    if (userInfo != null) {
                        request.getSession().setAttribute("user", userInfo);
                        request.getRequestDispatcher("main.jsp").forward(request, response);
                    } else {
                        request.getSession().setAttribute("msg", "用户名密码不对");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
            } else {
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request,response);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
