package com.feng.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
/*
*
*
* */
public class RemeberMeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强制类型转换，不然获取不到cookie
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String methodName = request.getParameter("method");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String autoLogin = request.getParameter("autoLogin");
        /*
        * 先判断 是否 是自动登录      仅仅是判断
        * */
        if (autoLogin == null || autoLogin.length()==0){    //判断时否选择自动登陆
            //以下没有选中自动登录
            Cookie[] cookies = request.getCookies();
            if (cookies !=null || cookies.length>0){
                for(Cookie cookie : cookies){
                    if ("autoLogin".equals(cookie.getName())){
                        cookie.setMaxAge(0);                    //清除 cookie中原来的autoLogin
                    }
                    response.addCookie(cookie);
                }
            }
        }else{
            //这里是 autoLogin!=null  即选中了自动登录，则保存
            Cookie cookie = new Cookie("autoLogin",autoLogin);
            cookie.setMaxAge(5*24*60*60);
            response.addCookie(cookie);
        }



        //先看是否是login，  否则就放行
        if ("login".equalsIgnoreCase(methodName)) {
             //记住密码
            if (rememberMe == null || rememberMe.length() == 0) {    //判断是否记住密码，在判断用户名、密码是否为空
                //没有记住密码，则清除密码
                Cookie[] cookies = request.getCookies();
                if (cookies != null || cookies.length > 0) {
                    for (Cookie cookie : cookies) {
                        if ("name".equals(cookie.getName())) {
                            cookie.setMaxAge(0);        //清除
                        }
                        if ("password".equals(cookie.getName())) {
                            cookie.setMaxAge(0);
                        }
                        response.addCookie(cookie);
                    }
                }
            } else {
                    //Cookie 默认是不能存放中文的，解决方法： 先解码，存放到cookie，然后从cookie取的时候在编码
                    String encode = URLEncoder.encode(name, "UTF-8");

                    Cookie cookie1 = new Cookie("name", encode);
                    Cookie cookie2 = new Cookie("password", password);
                    cookie1.setMaxAge(5 * 24 * 60 * 60);
                    cookie2.setMaxAge(5 * 24 * 60 * 60);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
            }
            filterChain.doFilter(request,response);
        }else{
            filterChain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {
    }
}
