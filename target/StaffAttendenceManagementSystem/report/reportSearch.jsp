<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>用户管理</title>
  <link href="../css/style2.css" rel="stylesheet" type="text/css" />
  <link href="../css/select.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="../css/style.css" />
  <link rel="stylesheet" type="text/css" href="../css/WdatePicker.css" />
  <link rel="stylesheet" type="text/css" href="../css/skin_/form.css" />
  <link href="umeditor/themes/default/_css/umeditor.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
  <script type="text/javascript" src="../js/select-ui.min.js"></script>

  <script type="text/javascript" src="../js/global.js"></script>
  <script type="text/javascript" src="../js/jquery.select.js"></script>
  <script type="text/javascript" src="../js/WdatePicker.js"></script>
  <script type="text/javascript" src="../js/umeditor.config.js"></script>
  <script type="text/javascript" src="../js/editor_api.js"></script>
  <script type="text/javascript" src="../umeditor/lang/zh-cn/zh-cn.js"></script>
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

          //全选复选框
          $("#reportAllCheckbox").click(function () {
              if ($(this).is(":checked")){
                  $(".reportCheckbox").attr("checked","checked");
              } else {
                  $(".reportCheckbox").removeAttr("checked");
              }
          })
          //单个复选框
          $(".reportCheckbox").click(function () {
              var flag = false;
              $(".reportCheckbox").each(function () {
                  if (!$(this).is(":checked")){
                      flag = true;
                  }
              });

              if (flag){
                  $("#reportAllCheckbox").removeAttr("checked");
              } else {
                  $("#reportAllCheckbox").attr("checked","checked");
              }
          })
      });

      //删除多个日报
      function deleteManyReport() {
          var account ="";
          if (confirm("确定要删除 选中的所有数据么？")){
              $("input[name=reportCheckedbox]").each(function () {
                  if ($(this).attr("checked")){
                      account += $(this).val()+",";
                      console.log(account);
                  }
              })
          }

          $.ajax({
              type : 'POST',
              url : '/report',
              data : {method:'deleteManyReport',account:account},
              success : function () {
                  alert("删除成功！！！");
              }
          })

      }
  </script>
</head>

<body>

<div class="place">
  <span>位置：</span>
  <ul class="placeul">
    <li><a href="#">日报管理</a></li>
  </ul>
</div>

<!--查询条件-->
<br />
<br />
<ul class="seachform">
  <li>
    <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名</label><input name="" type="text" class="scinput" /></li>
  <li>
    <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始日期</label><input name="" type="text" class="scinput" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li>
  <li>
    <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束日期</label><input name="" type="text" class="scinput" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></li>

  <li><label>&nbsp;</label><input name="" type="button" class="scbtn" value="查询"/></li>

</ul>
</div>

<div class="tools">

  <ul class="toolbar">
    <li class="click">
      <span><img src="../images/t01.png" /></span><a href="report/reportInsert.jsp" >添加</a></li>     <%-- target="_self"--%>
    <li class="click"><img src="../images/trash.png" /></span><a href="#"  onclick="deleteManyReport()">删除</a></li>   <%-- target="rightFrame" --%>
  </ul>
</div>


<table class="tablelist"><tbody><tr><td><table class="tablelist"><tbody><tr><td><table class="tablelist">
  <tbody>
  <tr>
    <td><table class="tablelist">
      <thead>
      <tr>
        <th width="3%"><input name="" id="reportAllCheckbox" type="checkbox" value="" /></th>
        <th width="7%">工号<i class="sort"><img src="../images/px.gif" /></i></th>
        <th width="11%">姓名</th>
        <th width="10%">日期</th>
        <th width="9%">作业进度</th>
        <th width="11%">作业内容</th>
        <th width="7%">问题点</th>
        <th width="8%">联络事项</th>
        <th width="13%">操作</th>
      </tr>
      </thead>
      <tbody>

        <c:forEach var="report" items="${sessionScope.reports}">
          <tr>
            <td><input type="checkbox" class="reportCheckbox" name="reportCheckedbox" value="${report.account}"/></td>
            <td>${report.account}</td>
            <td>${report.name}</td>
            <td>
              <fmt:formatDate value="${report.report_date}" type="date"></fmt:formatDate>
            </td>
            <td>${report.work_process}</td>
            <td>${report.work_content}</td>
            <td>${report.problem}</td>
            <td>${report.other}</td>
            <td>
              <span><a href="/report?method=toUpdateReportPage&reportAccount=${report.account}" class="tablelink"><img src="../images/user_edit.png" />修改</a>
              <a href="/report?method=deleteReportByAccount&reportAccount=${report.account}" class="tablelink" onclick="confirm('确定要删除吗？')"> <img src="../images/trash.png" />删除</a></span>
            </td>
          </tr>
        </c:forEach>

      </tbody>
    </table></td>
  </tr>
  </tbody>
</table>
</td>
</tr>
</tbody>
</table></td>
</tr>
</tbody>
</table>


<div class="pagin">
  <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
  <ul class="paginList">
    <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
    <li class="paginItem"><a href="javascript:;">1</a></li>
    <li class="paginItem current"><a href="javascript:;">2</a></li>
    <li class="paginItem"><a href="javascript:;">3</a></li>
    <li class="paginItem"><a href="javascript:;">4</a></li>
    <li class="paginItem"><a href="javascript:;">5</a></li>
    <li class="paginItem more"><a href="javascript:;">...</a></li>
    <li class="paginItem"><a href="javascript:;">10</a></li>
    <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
  </ul>
</div>


<div class="tip">
  <div class="tiptop"><span>提示信息</span><a></a></div>

  <div class="tipinfo">
    <span><img src="images/ticon.png" /></span>
    <div class="tipright">
      <p>是否确认对信息的修改 ？</p>
      <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
    </div>
  </div>

  <div class="tipbtn">
    <input name="" type="button"  class="sure" value="确定" />&nbsp;
    <input name="" type="button"  class="cancel" value="取消" />
  </div>

</div>




</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>

