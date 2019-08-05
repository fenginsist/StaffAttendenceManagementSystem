<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>加班管理</title>
  <link href="../css/style2.css" rel="stylesheet" type="text/css" />
  <link href="../css/select.css" rel="stylesheet" type="text/css" />
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
          $("#allRestChecked").click(function () {
              if ($(this).is(":checked")){
                  $(".restCheckbox").attr("checked","checked");
              } else {
                  $(".restCheckbox").removeAttr("checked");
              }
          })

          //校验
          $(".restCheckbox").click(function () {
              var flag = false;
              $(".restCheckbox").each(function () {
                  if (!$(this).is(":checked")){
                      flag = true;
                  }
              })
              if (flag){
                  $("#allRestChecked").removeAttr("checked");
              } else {
                  $("#allRestChecked").attr("checked","checked");
              }
          })
      });

      //删除单个
      function deleteRest(account) {
          $.ajax({
              type : 'GET',
              url : "/rest",
              data : {method:'deleteRest',account:account},
              success : function () {
                  alert("删除成功");
              }
          })
      }

      //修改
      function toUpdateRest(account) {
          $.ajax({
              type : 'POST',
              url : "/rest",
              data : {method:'toUpdateRestPage',account:account},
              success : function () {
              }
          })
      }

      //删除多个
    function deleteManyRest() {
        if (confirm("确定要删除选中的数据么？")) {
            var account = "";
            $("input[name=restCheckbox]").each(function () {
                if ($(this).attr("checked")) {
                    account += $(this).val() + ",";
                    console.log(account);
                }
            })

            $.ajax({
                type : 'POST',
                url : "/rest",
                data : {method:'deleteManyRest',account:account},
                success : function () {
                    alert("删除成功！！！"+account);
                }
            })

        }

    }
  </script>
</head>

<body>

<div class="place">
  <span>位置：</span>
  <ul class="placeul">
    <li><a href="#">休假管理</a></li>
  </ul>
</div>

<!--查询条件-->
<br />
<br />
<div>
<ul class="seachform">
  <li>
    <label> 休假日期:</label><input name="" type="text" class="scinput"  value="请选择开始日期" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></li>
  <li>  <label> 到</label><input name="" type="text" class="scinput" value="请选择结束日期" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></li>
  <li>
    <label>状态：</label>
    <div class="vocation">
      <select class="select3">
        <option>--请选择--</option>
        <option>审批中</option>
        <option>已批准</option>
        <option>已驳回</option>
      </select>
    </div>
  </li>
  <li><label>&nbsp;</label><input name="" type="button" class="scbtn" value="查询"/></li>
</ul>
</div>

<div class="tools">
  <ul class="toolbar">
    <li class="click"><span><img src="../images/t01.png" /></span><a href="restmanager/restInsert.jsp" target="_self">添加</a></li>
    <li class="click"><img src="../images/trash.png" /></span><a href="#" onclick="deleteManyRest()" >删除</a></li>  <%--target="rightFrame" --%>
  </ul>
</div>

<table class="tablelist"><tbody><tr><td><table class="tablelist"><tbody><tr><td><table class="tablelist">
  <tbody>
  <tr>
    <td><table class="tablelist">
      <thead>
      <tr>
        <th width="3%"><input name="" id="allRestChecked" type="checkbox" value="" /></th>
        <th width="7%">工号<i class="sort"><img src="../images/px.gif" /></i></th>
        <th width="11%">姓名</th>
        <th width="10%">休假开始日期</th>
        <th width="9%">休假开始时间</th>
        <th width="10%">休假结束日期</th>
        <th width="11%">休假终了时间</th>
        <th width="7%">休假时间小计</th>
        <th width="8%">状态</th>
        <th width="13%">操作</th>
      </tr>
      </thead>
      <tbody>

        <c:forEach var="rest" items="${sessionScope.rests}" >
          <tr>
            <td><input type="checkbox" name="restCheckbox" class="restCheckbox" value="${rest.account}" /></td>
            <td>${rest.account}</td>
            <td>${rest.name}</td>
            <td><fmt:formatDate value="${rest.rest_start_date}" type="date"></fmt:formatDate> </td>
            <td><fmt:formatDate value="${rest.start_time}" type="date"></fmt:formatDate></td>
            <td><fmt:formatDate value="${rest.rest_end_date}" type="date"></fmt:formatDate></td>
            <td><fmt:formatDate value="${rest.end_time}" type="date"></fmt:formatDate></td>
            <td><fmt:formatDate value="${rest.rest_time}" type="date"></fmt:formatDate></td>
            <td>${rest.rest_cause}</td>
            <td>
              <span><a href="#" onclick="toUpdateRest(${rest.account})" class="tablelink"><img src="../images/user_edit.png" />修改</a>
              <a href="#" class="tablelink" onclick="confirm('确定要删除吗？') ? deleteRest(${rest.account}) :false"> <img src="../images/trash.png" />删除</a></span>
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
