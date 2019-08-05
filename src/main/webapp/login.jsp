<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>员工考勤管理系统</title>
    <link rel="stylesheet" type="text/css" href="css/style1.css" />
    <link rel="stylesheet" type="text/css" href="css/body.css"/>
</head>
<%
    //取Cookie中存在的值
    String name = "";
    String password = "";
    Cookie[] cookies = request.getCookies();
    if (cookies!=null || cookies.length>0) {           //先判断是否为空，否则为空时会报空指针异常
        for (Cookie cookie : cookies) {
            if ("name".equals(cookie.getName())) {
                name = URLDecoder.decode(cookie.getValue(),"utf-8");    //取到的是解码之后的字符串， 需要进行编码
            }
            if ("password".equals(cookie.getName())) {
                password = cookie.getValue();
            }
            response.addCookie(cookie);
        }
    }
%>
<body>
<div class="container" id="app">

    <section id="content">
        <form action="/main?method=login" method="post">
            <h1>员工考勤管理系统</h1>
            <div>
                <input type="text" v-model="username" placeholder="用户名"  name="name" value="<%=name%>" />
            </div>
            <div>
                <input type="password" v-model="password" placeholder="密码"  name="password" value="<%=password%>" />
            </div>
            <div class="">
                <span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>			</div>
            <div>
                <!-- <input type="submit" value="Log in" /> -->
                <input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
                <label> <input name="rememberMe" type="checkbox" value="remberMe" checked  />记住密码</label>
                <label> <input name="autoLogin" type="checkbox" value="autoLogin" />自动登录</label>
            </div>
        </form><!-- form -->

    </section><!-- content -->
</div>
<br><br><br><br>

</body>
<script type="text/javascript" src="js/vue.js"></script>
<script type="text/javascript">

    // var vm = new Vue({
    //     el: "#app",
    //     data:{
    //         checked : true,
    //         username : null,
    //         password : null,
    //     },
    //     methods:{
    //         click:function () {
    //             var self = this;
    //             if(self.checked == true){
    //                 var username = self.data.username;
    //                 var password = self.data.password;
    //                 alert(username+":"+password);
    //                 // alert("aa")
    //             }
    //         }
    //     }
    // })

</script>
</html>