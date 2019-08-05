<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
                        <span class="nav-text">审批管理</span>
                    </a>
                    <ul class="subnav">
                        <li class="subnav-li" data-id="15"  href="/work?method=toWorkSearchPage" >
                            <a href="javascript:;" class="ue-clear" >
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">加班审批</span>
                            </a>
                        </li>

                        <li class="subnav-li" data-id="16"  href="/rest?method=toRestSearch" >
                            <a href="javascript:;" class="ue-clear" >
                                <i class="subnav-icon"></i>
                                <span class="subnav-text">休假审批</span>
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
