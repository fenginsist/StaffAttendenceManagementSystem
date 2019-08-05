<%--
  Created by IntelliJ IDEA.
  User: 安辰
  Date: 2019/3/14
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>加班申请添加</title>
  <link href="../css/style2.css" rel="stylesheet" type="text/css" />
  <link href="../css/select.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
  <script type="text/javascript" src="../js/select-ui.min.js"></script>
  <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
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
          document.forms[0].action="workSearch.html";
          document.forms[0].submit();
      }
  </script>

</head>

<body>
<form action="myForm">
  <div class="place">
    <span>位置：</span>
    <ul class="placeul">
      <li><a href="#">加班管理</a></li>
      <li><a href="#">修改加班申请</a></li>
    </ul>
  </div>

  <div class="formbody">

    <div class="formtitle"><span>加班申请</span></div>

    <ul class="forminfo">
      <li>
        <label>工号</label>
        </label><input type="text" class="dfinput" value="10001" readonly="readonly"/>
      </li>
      <li>
        <label>姓名</label>
        </label><input type="text" class="dfinput" value="admin" readonly="readonly"/>
      </li>
      <li>
        <label>加班日期 <font color="red">*</font></label>
        <input type="text" class="dfinput"  name="work_date" onClick="WdatePicker({work_date:'',dateFmt:'yyyy-MM-dd'})" value="2013-09-09"/>
      </li>
      <li>
        <label>开始时间 <font color="red">*</font></label>
        <input type="text" class="dfinput"  name="" value="20:00"/> (HH:mm)
      </li>
      <li>
        <label>终了时间 <font color="red">*</font></label>
        <input type="text" class="dfinput"  name="" value="22:00"/> (HH:mm)
      </li>
      <li>
        <label>加班时间小计 </label>
        <input type="text" class="dfinput"  name="" readonly value="2.0"/>
      </li>
      <li>
        <br />
        <label>加班原因 <font color="red">*</font></label><textarea rows="5" cols="10" id="tomorrow_plan" required="required"  name="tomorrow_plan" style="width: 500px; height: 30px; padding-left:5px; border: 1px solid #eaeff2; margin-top: 20px;ime-mode:disabled;" maxlength="255">需要开会解决技术问题</textarea></li>
      <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="saveButton()"/></li>
    </ul>
  </div>
</form>
</body>
</html>

