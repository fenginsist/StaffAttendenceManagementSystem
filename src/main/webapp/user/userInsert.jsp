<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安辰
  Date: 2019/3/9
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>用户添加</title>
  <link href="../css/style2.css" rel="stylesheet" type="text/css"/>

  <link href="../css/select.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
  <script type="text/javascript" src="../js/select-ui.min.js"></script>
  <script type="text/javascript" src="../js/WdatePicker.js"></script>
  <script type="text/javascript">
      KE.show({
          id: 'content7',
          cssPath: './index.css'
      });
  </script>

  <script type="text/javascript">
      $(document).ready(function (e) {
          $(".select1").uedSelect({
              width: 345
          });
          $(".select2").uedSelect({
              width: 167
          });
          $(".select3").uedSelect({
              width: 100
          });
      });
  </script>
  <script type="es6">
      function saveButton() {
          var phone = $(".dfinput").val();
          console.log("************",phone);
          if (phone.toString().length <= 11) {
              alert("请输入！")
          }
          // document.forms[0].action="userSearch.html";
          document.forms[0].action = "/main?method=userInsert";
          document.forms[0].submit();
      }
  </script>

  <script language="javascript">
      function saveButton() {
          document.forms[0].action = "/main?method=userInsert";
          document.forms[0].submit();
      }

      function checkPhone() {
          var phone = document.getElementById('phone').value;
          if (!(/^1[34578]\d{9}$/.test(phone))) {
              alert("手机号码有误，请重填");
              return false;
          }
      }
  </script>

</head>

<body>
<form action="userFrom" method="post">
  <div class="place">
    <span>位置：</span>
    <ul class="placeul">
      <li><a href="/main?method=userSearch">用户管理</a></li>
      <li><a href="#">添加用户</a></li>
    </ul>
  </div>

  <div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">
      <li>
        <label>工号 <font color="red">*</font></label>
        </label><input name="account" type="text" class="dfinput"/></li>
      <li>
        <label>密码 <font color="red">*</font></label>
        <input name="password" type="password" class="dfinput"/>
      </li>
      <li>
        <label>确认密码 <font color="red">*</font>
        </label><input name="password1" type="password" class="dfinput"/>
      </li>
      <li>
        <label>姓名 <font color="red">*</font></label>
        </label><input name="name" type="text" class="dfinput"/>
      </li>
      <li><label>部门</label>
        <div class="vocation">
          <select class="select3" name="department_name">
            <option>--请选择--</option>
            <c:forEach var="dept" items="${sessionScope.department}">
              <option>${dept.department_name}</option>
            </c:forEach>
          </select>
        </div>
      </li>
      <li>
        <label>性别</label>
        <cite>
          <input name="sex" type="radio" value="1" checked="checked"/>
          男&nbsp;&nbsp;&nbsp;&nbsp;
          <input name="sex" type="radio" value="2"/>
          女
        </cite>
      </li>
      <li><label>手机号码 <font color="red">*</font></label>
        <input name="mobile" type="text" class="dfinput"  value=""/>
      </li>
      <li><label>出生日期</label>
        <input name="birthday" type="text" class="dfinput" value="" placeholder="请输入格式如：2000-00-00"
               onClick="WdatePicker({startDate:'',dateFmt:'yyyy-MM-dd'})"/>
      </li>
      <li><label>邮箱 <font color="red">*</font></label>
        <input name="email" type="text" class="dfinput" value=""/>
      </li>
      <li><label>&nbsp;</label>
        <input name="" type="button" class="btn" value="确认保存" onclick="saveButton()"/>
      </li>
    </ul>
  </div>
</form>
</body>
</html>

