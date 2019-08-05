<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="css/style2.css" rel="stylesheet" type="text/css"/>
    <!--<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>-->

    <script src="js/node_modules/vue/dist/vue.js"></script>
    <%--<!--<script type="text/javascript" src="js/jquery.js"></script>-->--%>
</head>
<%

    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    String format = simpleDateFormat.format(date);

%>
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
    </ul>
</div>

<div class="mainindex">


    <div class="welinfo">
        <span><img src="images/sun.png" alt="天气"/></span>
        <b><span>${sessionScope.user.name}</span>早上好，欢迎使用考勤管理系统</b>
    </div>

    <div class="welinfo" id="app">
        <span><img src="images/time.png" alt="时间"/></span>
        <i>您本次次登录的时间</i>
        <%=format%>
        {{msg}}
    </div>
</div>
<script type="es6">
    let vm = new Vue({
        el: "#app",
        data: {
            nowTime: "3333",
            msg:"冯凡利"
        },
        mounted() {
            this.nowTime = new Date().toLocaleDateString();
            console.log(1);
        },
        methods: {}
    })
</script>
</body>
</html>

