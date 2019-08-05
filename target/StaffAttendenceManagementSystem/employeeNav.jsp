<%--
  Created by IntelliJ IDEA.
  User: 安辰
  Date: 2019/3/5
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/skin_/nav.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/global.js"></script>
    <title>底部内容页</title>
</head>

<body>

<div id="rounded-corner" >
    <div id="bd">
        <div class="sidebar">
            <div class="sidebar-bg"></div>
            <i class="sidebar-hide"></i>
            <ul class="nav">



                <li class="nav-li last-nav-li">
                    <a href="javascript:;" class="ue-clear">
                        <i class="nav-ivon"></i>
                        <span class="nav-text">日报管理</span>
                    </a>
                    <ul class="subnav">
                        <li class="subnav-li" data-id="12"  href="/report?method=toReportSearchPage" >
                            <a href="javascript:;" class="ue-clear" >
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">日报管理</span>
                            </a>
                        </li>
                    </ul>
                </li>



                <li class="nav-li last-nav-li">
                    <a href="javascript:;" class="ue-clear">
                        <i class="nav-ivon"></i>
                        <span class="nav-text">休假管理</span>
                    </a>
                    <ul class="subnav">
                        <%--<li class="subnav-li" data-id="14"  href="restmanager/restSearch.html" >--%>
                        <li class="subnav-li" data-id="14"  href="/rest?method=toRestSearch" >
                            <a href="javascript:;" class="ue-clear" >
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">休假申请</span>
                            </a>
                        </li>
                    </ul>
                </li>


                <li class="nav-li last-nav-li">
                    <a  href="javascript:;" class="ue-clear">
                        <i class="nav-ivon"></i>
                        <span class="nav-text">个人信息修改</span>
                    </a>
                    <ul class="subnav">
                        <li class="subnav-li" data-id="17"  href="/selfUpdate?method=toUpdateSelfPage" >
                            <a href="javascript:;" class="ue-clear" >
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">个人信息修改</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="nav-li last-nav-li">
                    <a  href="javascript:;" class="ue-clear">
                        <i class="nav-ivon"></i>
                        <span class="nav-text">我的桌面</span>
                    </a>
                    <ul class="subnav">
                        <li class="subnav-li" data-id="18"  href="mydesktop/mydesktop.html" >
                            <a href="javascript:;" class="ue-clear" >
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">我的桌面</span>
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


    $('.sidebar h2').click(function(e) {
        $('.tree-list').toggleClass('outwindow');
        $('.nav').toggleClass('outwindow');
    });

    $(document).click(function(e) {
        if(!$(e.target).is('.tab-more')){
            $('.tab-more').removeClass('active');
            $('.more-bab-list').hide();
        }
    });
</script>
</html>

