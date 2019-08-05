<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>用户管理</title>
  <link href="../css/style2.css" rel="stylesheet" type="text/css"/>
  <link href="../css/select.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
  <script type="text/javascript" src="../js/select-ui.min.js"></script>
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

          /*  全选。。复选框*/
          $("#allSelect").click(function () {

              if ($(this).is(":checked")) {
                  alert("选中复选框")
                  $(".userCheckbox").attr("checked", "checked");
              } else {
                  alert("没有选中复选框")
                  $(".userCheckbox").removeAttr("checked");
              }

          })

          //当用户的单个复选框任有一个没有选中时，则总复选框 不选中。否则 选中
          $(".userCheckbox").click(function () {
              var flag = false;
              $(".userCheckbox").each(function () {
                  if (!$(this).is(":checked")) {
                      flag = true;
                  }
              })
              if (flag) {       //flag 为 true
                  $("#allSelect").removeAttr("checked");
              } else {
                  $("#allSelect").attr("checked", "checked");
              }
          })
      });

      //删除单个 用户
      function toDeleteUser(account) {
          console.log("22222222222222222"+account);
          $.ajax({
              type : 'POST',
              url : "/main",
              data : {method:'deleteUserByAccount',account:account},
              success :function () {
                  alert("删除成功")
              }
          })

      }

      //删除多个 用户
      function deleteManyUserByAccount() {
          if (confirm("你确定要删除选中的用户么？")) {
              //获取CheckBox的选中的值
              var account = "";
              $("input[name=userCheckbox]").each(function () {
                  if ($(this).attr("checked")) {
                      account += $(this).val() + ",";
                      console.log(account);
                  }
              })

              // $.get("/main?method=deleteManyUser&amp;account="+account,function () {
              //     alert("删除成功");
              // })
              window.location.href = "/main?method=deleteManyUserByAccount&account=" + account;
          }
      }

      // function select() {
      //     var name = $("#name").val();
      //     var department_name = $("#department_name").val();
      //
      //     console.log("11111111111111"+name+department_name);
      //
      //     $.ajax({
      //         type : 'POST',
      //         url : "/main",
      //         date : {method:'getUserByNameAndDept',name:name,department_name:department_name},
      //         success :function () {
      //         }
      //     })
      // }

  </script>
</head>
<%
  HttpSession session1 = request.getSession();
  session1.getAttribute("user");
%>
<body>

<div class="place">
  <span>位置：</span>
  <ul class="placeul">
    <li><a href="#">用户管理</a></li>
  </ul>
</div>

<!--查询条件-->
<br/>
<br/>
<div>
  <%--如果 get 方式 不会乱码，因为过滤器可以进行过滤器，但是不对post方式进行过滤--%>
  <form action="/main?method=getUserByNameAndDept" method="post" >
    <ul class="seachform">
      <li><label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名</label>
        <input name="name" id="name" type="text" class="scinput"/>
      </li>

      <li><label>部门</label>
        <div class="vocation">
          <select class="select3" name="department_name" id="department_name">
            <option>--请选择--</option>
            <c:forEach var="dept" items="${sessionScope.department}">
                <option value="${dept.department_name}">${dept.department_name}</option>
            </c:forEach>
          </select>
        </div>
      </li>
      <li><label>&nbsp;</label><input name="" type="submit"  class="scbtn" value="查询"/></li>
    </ul>
  </form>
</div>

<div class="tools">

  <ul class="toolbar">
    <li class="click">
      <span><img src="../images/t01.png"/></span>
      <%--<a href="userInsert.html" target="rightFrame">添加</a>--%>
      <a href="/main?method=userInsertPage">添加</a> <%-- target="rightFrame"  去掉它就可以把增加页面放到最右边了 --%>
    </li>
    <li class="click">
      <img src="../images/trash.png"/></span>
      <a href="#" onclick="deleteManyUserByAccount()" >删除</a>   <%-- target="rightFrame"--%>
    </li>
  </ul>
</div>


<table class="tablelist">
  <tbody>
  <tr>
    <td>
      <table class="tablelist">
        <tbody>
        <tr>
          <td>
            <table class="tablelist">
              <tbody>
              <tr>
                <td>
                  <table class="tablelist">
                    <thead>
                    <tr>
                      <th><input id="allSelect" type="checkbox" name="" value=""/></th>
                      <th width="7%">工号<i class="sort"><img src="../images/px.gif"/></i></th>
                      <th width="11%">姓名</th>
                      <th width="10%">部门</th>
                      <th width="9%">职务</th>
                      <th width="11%">注册时间</th>
                      <th width="5%">性别</th>
                      <th width="10%">手机</th>
                      <th width="9%">出生日期</th>
                      <th width="12%">邮箱</th>
                      <th width="13%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    %>
                    <c:forEach var="users" items="${sessionScope.pageInfoBean.list}">
                      <tr>
                        <td><input class="userCheckbox" name="userCheckbox" type="checkbox" value="${users.account}"/></td>
                        <td>${users.account}</td>
                        <td>${users.name}</td>
                        <td>${users.department_name}</td>
                        <c:if test="${users.user_type==0}"><td>超级管理员</td></c:if>
                        <c:if test="${users.user_type==1}"><td>经理</td></c:if>
                        <c:if test="${users.user_type==2}"><td>员工</td></c:if>
                        <td><fmt:formatDate value="${users.create_time}" type="date"  ></fmt:formatDate></td>
                        <c:if test="${users.sex == 1}"><td>男</td></c:if>
                        <c:if test="${users.sex == 2}"><td>女</td></c:if>
                        <td>${users.mobile}</td>
                        <td><fmt:formatDate value="${users.birthday}" type="date"  ></fmt:formatDate></td>
                        <td>${users.email}</td>
                        <td>
                           <span>
                               <%--<a href="userUpdate.html" clas,s="tablelink">--%>
                               <%--<a href="/UserUpdatePageServlet?id=${users.id}" class="tablelink">--%>
                               <a href="/main?method=updateUserPage&id=${users.id}" class="tablelink">
                                    <img src="../images/user_edit.png"/>修改
                               </a>
                               <a href="#" class="tablelink"
                                  onclick="confirm('确定要删除吗？') ? toDeleteUser(${users.account}) : false">
                                   <img src="../images/trash.png"/>删除
                               </a>
                           </span>
                        </td>
                      </tr>
                    </c:forEach>

                    </tbody>
                  </table>
                </td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
        </tbody>
      </table>
    </td>
  </tr>
  </tbody>
</table>


<div class="pagin">
  <%--<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>--%>
  <div class="message">共<i class="blue">${sessionScope.pageInfoBean.totalRecord}</i>条记录，
    当前显示第&nbsp;<i class="blue">${sessionScope.pageInfoBean.pageNum}&nbsp;</i>页</div>

    <%--<ul>
      <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
      <li class="paginItem"><a href="javascript:;">1</a></li>
      <li class="paginItem current"><a href="javascript:;">2</a></li>
      <li class="paginItem"><a href="javascript:;">3</a></li>
      <li class="paginItem"><a href="javascript:;">4</a></li>
      <li class="paginItem"><a href="javascript:;">5</a></li>
      <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
    </ul>--%>
    <ul class="paginList">
      <%--如果当前页为第一页，就没有上一页, < 这个超链接显示 --%>
      <c:if test="${sessionScope.pageInfoBean.pageNum == 1}">
        <c:forEach begin="${sessionScope.pageInfoBean.firstPageNum}" end="${sessionScope.pageInfoBean.lastPageNum}" step="1" var="i">
          <c:if test="${sessionScope.pageInfoBean.pageNum == i}">
            <li class="paginItem current"><a href="javascript:;">${i}</a></li>    <%--当前--%>
          </c:if>
          <c:if test="${sessionScope.pageInfoBean.pageNum != i}">
            <li class="paginItem"><a href="/main?method=userSearch&pageNum=${i}">${i}</a></li>
          </c:if>
        </c:forEach>

        <li class="paginItem">
          <a href="/main?method=userSearch&pageNum=${sessionScope.pageInfoBean.pageNum+1}" >
            <span class="pagenxt"></span>
          </a>
        </li>
      </c:if>


      <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页的超链接显示 --%>
      <c:if test="${sessionScope.pageInfoBean.pageNum > 1 && sessionScope.pageInfoBean.pageNum < sessionScope.pageInfoBean.totalPage}">
        <li class="paginItem"><a href="/main?method=userSearch&pageNum=${sessionScope.pageInfoBean.pageNum-1}">
          <span class="pagepre"></span></a></li>
        <c:forEach begin="${sessionScope.pageInfoBean.firstPageNum}" end="${sessionScope.pageInfoBean.lastPageNum}" step="1" var="i">
          <c:if  test="${sessionScope.pageInfoBean.pageNum == i}">
            <li class="paginItem current"><a href="javascript:;">${i}</a></li>
          </c:if>
          <c:if test="${sessionScope.pageInfoBean.pageNum != i}">
            <li class="paginItem">
              <a href="/main?method=userSearch&pageNum=${i}">${i}</a></li>
          </c:if>
        </c:forEach>
        <li class="paginItem"><a href="/main?method=userSearch&pageNum=${sessionScope.pageInfoBean.pageNum+1}">
          <span class="pagenxt"></span></a></li>
      </c:if>


      <%--如果当前页是最后一页，则只有上一页这个超链接显示，下一页没有 --%>
      <c:if test="${sessionScope.pageInfoBean.pageNum ==sessionScope.pageInfoBean.totalPage}">
        <li class="paginItem">
          <a href="/main?method=userSearch&pageNum=${sessionScope.pageInfoBean.pageNum-1 }">
            <span class="pagepre"></span></a></li>
        <c:forEach begin="${sessionScope.pageInfoBean.firstPageNum}" end="${sessionScope.pageInfoBean.lastPageNum}" step="1" var="i">
          <c:if test="${sessionScope.pageInfoBean.pageNum == i}">
            <li class="paginItem">${i}</li>
          </c:if>
          <c:if test="${sessionScope.pageInfoBean.pageNum != i}">
            <li class="paginItem"><a href="/main?method=userSearch&pageNum=${i}">${i}</a></li>
          </c:if>
        </c:forEach>
      </c:if>

  </ul>
</div>


<div class="tip">
  <div class="tiptop"><span>提示信息</span><a></a></div>

  <div class="tipinfo">
    <span><img src="images/ticon.png"/></span>
    <div class="tipright">
      <p>是否确认对信息的修改 ？</p>
      <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
    </div>
  </div>

  <div class="tipbtn">
    <input name="" type="button" class="sure" value="确定"/>&nbsp;
    <input name="" type="button" class="cancel" value="取消"/>
  </div>

</div>


</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
