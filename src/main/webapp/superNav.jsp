<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/skin_/nav.css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/global.js"></script>
    <title>底部内容页</title>
</head>

<body>

<div id="rounded-corner">
    <div id="bd">
        <div class="sidebar">
            <div class="sidebar-bg"></div>
            <i class="sidebar-hide"></i>
            <ul class="nav" id="app">

                <li class="nav-li current">
                    <a href="javascript:;" class="ue-clear">
                        <i class="nav-ivon"></i>
                        <span class="nav-text">用户管理</span>
                    </a>
                    <ul class="subnav">
                        <!--这是右边的登录显示页面-->
                        <li class="subnav-li" href="index.jsp" data-id="1">
                            <a href="javascript:;" class="ue-clear">
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">首页</span>
                            </a>
                        </li>
                        <!--用户管理页面-->
                        <li class="subnav-li" href="/main?method=userSearch" data-id="2">
                            <a href="javascript:;" class="ue-clear">
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">用户管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <!--部门管理-->
                <li class="nav-li last-nav-li">
                    <a href="javascript:;" class="ue-clear">
                        <i class="nav-ivon"></i>
                        <span class="nav-text">部门管理</span>
                    </a>
                    <ul class="subnav">
                        <%--<li class="subnav-li" data-id="10" href="/dept/deptSearch.html">--%>
                        <li class="subnav-li" data-id="10" href="/dept?method=deptSearch">
                            <a href="javascript:;" class="ue-clear">
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">部门管理</span>
                            </a>
                        </li>
                    </ul>
                </li>


                <!--个人信息修改-->
                <li class="nav-li last-nav-li">
                    <a href="javascript:;" class="ue-clear">
                        <i class="nav-ivon"></i>
                        <span class="nav-text">个人信息修改</span>
                    </a>
                    <ul class="subnav">
                        <li class="subnav-li" data-id="17" href="/selfUpdate?method=toUpdateSelfPage">
                            <a href="javascript:;" class="ue-clear">
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">个人信息修改</span>
                            </a>
                        </li>
                    </ul>
                </li>

            </ul>
            <div class="tree-list outwindow">
                <div class="tree ztree"></div>
            </div>
        </div>
        <div class="main">
            <div class="title">
                <i class="sidebar-show"></i>
                <ul class="tab ue-clear">

                </ul>
                <i class="tab-more"></i>
                <i class="tab-close"></i>
            </div>
            <div class="content">
            </div>
        </div>
    </div>
</div>

<div class="more-bab-list">
    <ul></ul>
    <div class="opt-panel-ml"></div>
    <div class="opt-panel-mr"></div>
    <div class="opt-panel-bc"></div>
    <div class="opt-panel-br"></div>
    <div class="opt-panel-bl"></div>
</div>
</body>
<script type="text/javascript" src="js/nav.js"></script>
<script type="text/javascript" src="js/Menu.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
    var menu = new Menu({
        defaultSelect: $('.nav').find('li[data-id="1"]')
    });


    $.fn.zTree.init($(".tree"), setting, zNodes);


    $('.sidebar h2').click(function (e) {
        $('.tree-list').toggleClass('outwindow');
        $('.nav').toggleClass('outwindow');
    });

    $(document).click(function (e) {
        if (!$(e.target).is('.tab-more')) {
            $('.tab-more').removeClass('active');
            $('.more-bab-list').hide();
        }
    });
</script>

<script src="js/vue.js" type="text/javascript"></script>
<!--<script src="js/node_modules/axios/lib/axios.js"></script>-->

<script type="text/javascript">
    var vm = new Vue({
        el: "#app",
        data: {},
        methods: {
            userSearch: function () {
                //设置请求路径
                console.log(1);
                axios.get('/main?method=userSearch').then(function (result) {
                });

            }
        }
    })
</script>

</html>
