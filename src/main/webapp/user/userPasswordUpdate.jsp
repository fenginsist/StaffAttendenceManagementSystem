<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户添加</title>
    <link href="../css/style2.css" rel="stylesheet" type="text/css" />
    <link href="../css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="../js/select-ui.min.js"></script>
    <script type="text/javascript" src="../js/WdatePicker.js"></script>
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
            document.forms[0].action="/selfUpdate?method=updateSelf";
            document.forms[0].submit();
        }
    </script>

</head>

<body>
<form action="" method="post">

    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">个人信息修改</a></li>
        </ul>
    </div>

    <div class="formbody">

        <div class="formtitle"><span>基本信息</span></div>

        <ul class="forminfo">
            <li>
                <label>工号 </label>
                <input name="account" type="text" class="scinput" value="${sessionScope.newUser.account}" readonly="readonly"/></li>
            <li>
                <label>密码 <font color="red">*</font></label>
                <input name="password" type="password" class="dfinput" value="${sessionScope.newUser.password}"/>
            </li>
            <li>
                <label>确认密码 <font color="red">*</font></label>
                <input name="password1" type="password" class="dfinput" value="${sessionScope.newUser.password}" />
            </li>
            <li>
                <label>姓名 <font color="red">*</font></label>
                </label><input name="name" type="text" class="dfinput" value="${sessionScope.newUser.name}"/>
            </li>
            <li><label>部门</label>
                <div class="vocation">
                    <%--不能遍历--%>
                    <select class="select3" name="department_name" disabled="disabled">
                        <option>--请选择--</option>
                        <c:forEach var="dept" items="${sessionScope.depts}">
                            <option value="${dept.department_name}">${dept.department_name}</option>
                        </c:forEach>
                    </select>
                </div>
            </li>
            <li>
                <label>性别</label>
                <cite><input name="sex" type="radio" value="1"  <c:if test="${sessionScope.newUser.sex == 1}"> checked="checked" </c:if>  />男&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="sex" type="radio" value="2" <c:if test="${sessionScope.newUser.mobile == 2}"> checked="checked"</c:if> />女</cite></li>
            <li>
                <label>手机号码 <font color="red">*</font></label>
                <input name="mobile" type="text" class="dfinput"  value="${sessionScope.newUser.mobile}" />
            </li>
            <li>
                <label>出生日期</label>
                <input name="birthday" type="text" class="dfinput" value="<fmt:formatDate value="${sessionScope.newUser.birthday}" type="date"/>"  onClick="WdatePicker({work_date:'',dateFmt:'yyyy-MM-dd'})"/>
            </li>
            <li>
                <label>邮箱 <font color="red">*</font></label>
                <input name="email" type="text" class="dfinput" value="${sessionScope.newUser.email}" />
            </li>
            <li>
                <label>&nbsp;</label>
                <input  type="button" class="btn" value="确认保存" onclick="saveButton()"/>
            </li>
        </ul>
    </div>
</form>
</body>
</html>
