<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 安辰
  Date: 2019/3/11
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>部门管理</title>
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

            // 选中复选框
            $("#allDeptCheckbox").click(function () {
                if ($(this).is(":checked")){
                    alert("选中复选框");
                    $(".deptCheckbox").attr("checked","checked");
                }else {
                    alert("未选中复选框");
                    $(".deptCheckbox").remove("checked");
                }
            })

            $(".deptCheckbox").click(function () {
                var flag = false;
                $(".deptCheckbox").each(function () {
                    if (!$(this).is(":checked")){
                        flag = true;
                    }
                })
                if (flag){
                    $("#allDeptCheckbox").remove("checked");
                } else {
                    $("#allDeptCheckbox").attr("checked","checkes");
                }

            })

        });

        function deleteDept(depid) {
            if (confirm("确定删除么")) {
                console.log("11111111111"+depid);
                var URL = "/dept";
                var data = {method:'deleteDeptById',deptId:depid};
                $.ajax({
                    type : 'POST',
                    url : URL,
                    data : data,
                    success :function () {
                        alert("删除成功")
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
        <li><a href="#">部门管理</a></li>
    </ul>
</div>

<!--查询条件-->
<br />
<br />
<div>
    <form action="/dept?method=getDeptByName" method="post">
        <ul class="seachform">
            <li>
                <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门名称</label>
                <input name="department_name" type="text" class="scinput" />
            </li>
            <li><label>&nbsp;</label><input name="" type="submit"  class="scbtn" value="查询"/></li>
        </ul>
    </form>
</div>

<div class="tools">

    <ul class="toolbar">
        <li class="click">
            <span><img src="../images/t01.png" /></span>
            <a href="dept/deptInsert.jsp" target="_self">添加</a></li>
        <li class="click">
            <img src="../images/trash.png" />
            </span><a href="#" target="rightFrame" onclick="toDeleteDept()">删除</a>
        </li>
    </ul>
</div>


<table class="tablelist"><tbody><tr><td><table class="tablelist"><tbody><tr><td><table class="tablelist">
    <tbody>
    <tr>
        <td><table class="tablelist">
            <thead>
            <tr>
                <th width="5%" height="83"><input id="allDeptCheckbox" type="checkbox" /></th>
                <th width="12%">部门编号<i class="sort"><img src="../images/px.gif" /></i></th>
                <th width="16%">部门名称</th>
                <th width="15%">负责人</th>
                <th width="13%">人数</th>
                <th width="21%">注册时间</th>
                <th width="18%">操作</th>
            </tr>
            </thead>
            <tbody>
            <%--这是不可以调用 在用户那里 存的session的部门信息，
            因为要直接过来遍历需要先存部门信息，否则就不能调用，这里的存，也就是点击一下用户管理 --%>
                <c:forEach var="dept" items="${sessionScope.depts}">
                    <tr>
                        <td><input type="checkbox" class="deptCheckbox" name="" value=""/></td>
                        <td>${dept.department_id}</td>
                        <td>${dept.department_name}</td>
                        <td>${dept.manager}</td>
                        <td>${dept.total_user}</td>
                        <td>${dept.create_time}</td>
                        <td>
                            <span>
                               <%--<a href="userUpdate.html" clas,s="tablelink">--%>
                               <a href="/dept?method=updataDeptPage&deptId=${dept.department_id}" class="tablelink">
                                    <img src="../images/user_edit.png"  />修改
                               </a>
                               <%--<a href="/dept?method=deleteDeptById&id=${dept.department_id}" class="tablelink" onclick="confirm('确定要删除吗？')">--%>
                               <button  class="tablelink" onclick="deleteDept(${dept.department_id})">
                                   <img src="../images/trash.png" />删除
                               </button>
                           </span>
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
    <%--<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>--%>
    <div class="message">共<i class="blue">${sessionScope.pageInfoBean.totalRecord}</i>条记录，当前显示第&nbsp;<i class="blue">${sessionScope.pageInfoBean.pageNum}&nbsp;</i>页</div>
    <%--<ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
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
                    <li class="paginItem"><a href="/dept?method=deptSearch&pageNum=${i}">${i}</a></li>
                </c:if>
            </c:forEach>

            <li class="paginItem">
                <a href="/dept?method=deptSearch&pageNum=${sessionScope.pageInfoBean.pageNum+1}" >
                    <span class="pagenxt"></span>
                </a>
            </li>
        </c:if>


        <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页的超链接显示 --%>
        <c:if test="${sessionScope.pageInfoBean.pageNum > 1 && sessionScope.pageInfoBean.pageNum < sessionScope.pageInfoBean.totalPage}">
            <li class="paginItem"><a href="/dept?method=deptSearch&pageNum=${sessionScope.pageInfoBean.pageNum-1}">
                <span class="pagepre"></span></a></li>
            <c:forEach begin="${sessionScope.pageInfoBean.firstPageNum}" end="${sessionScope.pageInfoBean.lastPageNum}" step="1" var="i">
                <c:if  test="${sessionScope.pageInfoBean.pageNum == i}">
                    <li class="paginItem current"><a href="javascript:;">${i}</a></li>
                </c:if>
                <c:if test="${sessionScope.pageInfoBean.pageNum != i}">
                    <li class="paginItem">
                        <a href="/dept?method=deptSearch&pageNum=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
            <li class="paginItem"><a href="/dept?method=deptSearch&pageNum=${sessionScope.pageInfoBean.pageNum+1}">
                <span class="pagenxt"></span></a></li>
        </c:if>


        <%--如果当前页是最后一页，则只有上一页这个超链接显示，下一页没有 --%>
        <c:if test="${sessionScope.pageInfoBean.pageNum ==sessionScope.pageInfoBean.totalPage}">
            <li class="paginItem">
                <a href="/dept?method=deptSearch&pageNum=${sessionScope.pageInfoBean.pageNum-1 }">
                    <span class="pagepre"></span></a></li>
            <c:forEach begin="${sessionScope.pageInfoBean.firstPageNum}" end="${sessionScope.pageInfoBean.lastPageNum}" step="1" var="i">
                <c:if test="${sessionScope.pageInfoBean.pageNum == i}">
                    <li class="paginItem">${i}</li>
                </c:if>
                <c:if test="${sessionScope.pageInfoBean.pageNum != i}">
                    <li class="paginItem"><a href="/dept?method=deptSearch&pageNum=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
        </c:if>

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
