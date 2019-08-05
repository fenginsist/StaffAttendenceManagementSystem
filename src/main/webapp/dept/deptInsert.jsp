<%--
  Created by IntelliJ IDEA.
  User: 安辰
  Date: 2019/3/12
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户添加</title>
    <link href="../css/style2.css" rel="stylesheet" type="text/css" />
    <link href="../css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="../js/select-ui.min.js"></script>
    <script type="text/javascript">
        KE.show({
            id : 'content7',
            cssPath : './index.css'
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });
            $(".select2").uedSelect({
                width : 167
            });
            $(".select3").uedSelect({
                width : 100
            });
        });
    </script>
    <script language="javascript">
        function saveButton(){
            document.forms[0].action="/dept?method=addDept";
            document.forms[0].submit();
        }
    </script>

</head>

<body>
<form action="userFrom" method="post">
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">部门管理</a></li>
            <li><a href="#">添加部门</a></li>
        </ul>
    </div>

    <div class="formbody">

        <div class="formtitle"><span>基本信息</span></div>

        <ul class="forminfo">
            <li>
                <label>部门编号 <font color="red">*</font></label>
                </label><input name="department_id" type="text" class="dfinput" placeholder="不可添加，自动生成" readonly="readonly"/></li>
            <li>
                <label>部门名称 <font color="red">*</font></label>
                <input name="department_name" type="text" class="dfinput" />
            </li>
            <li>
                <label>负责人</label>
                <input name="manager" type="text" class="dfinput" />
            </li>

            <%--这里不插入总人数，总人数 应该在这个service层，去用户表中进行检索。--%>

            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="saveButton()"/></li>
        </ul>
    </div>
</form>
</body>
</html>
