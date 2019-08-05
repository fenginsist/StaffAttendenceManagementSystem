package com.feng.controller;

import com.feng.bean.DepartmentInfo;
import com.feng.bean.PageInfoBean;
import com.feng.bean.UserInfo;
import com.feng.service.DepartmentService;
import com.feng.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/dept")
public class DepartmentServlet extends BaseServlet {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("用户没有指定调用的具体方法");
        request.getRequestDispatcher("error.jsp").forward(request,response);
    }
    /*
    * 获取部门所有，并且进行遍历
    * 但是从 userServlet 中的session中，取不到值  ，不知为何？
    * */
    public void deptSearch(HttpServletRequest request, HttpServletResponse response){
        try {
            DepartmentService departmentService = new DepartmentService();


            //传入两个值，，当前页，和页大小
            int pageRecord = 5; //页的记录数
            String pageNum1 = request.getParameter("pageNum");
            if (pageNum1 == null){
                pageNum1 = "1";
            }
            Integer pageNum = Integer.valueOf(pageNum1);   //当前页
            PageHelper.startPage(pageNum,pageRecord);
            List<DepartmentInfo> departments = departmentService.getAllDepartment();
            //传入要连续显示多少页
            PageInfo<DepartmentInfo> info = new PageInfo<>(departments,5);        //这样也可以

            PageInfoBean pageInfoBean = new PageInfoBean();

            pageInfoBean.setPageNum(info.getPageNum());              //  当前页面
            pageInfoBean.setList(departments);                      //  页大小的集合
            pageInfoBean.setTotalRecord((int) info.getTotal());       //   所有记录
            pageInfoBean.setTotalPage(info.getPages());               //  获取所有的页

            pageInfoBean.setFirstPageNum(info.getNavigateFirstPage());//  分页的第一个页面的页号
            pageInfoBean.setLastPageNum(info.getNavigateLastPage());  //  分页的最后一个页面的页号


            HttpSession session = request.getSession();
            session.setAttribute("pageInfoBean",departments);

            request.getRequestDispatcher("dept/deptSearch.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //删除用户 通过id
    public void deleteDeptById(HttpServletRequest request, HttpServletResponse response){
        try {
            System.out.println("进入了   delete DEPT");
            String id = request.getParameter("deptId");
            System.out.println("要删除的deptId::"+id);
            DepartmentService departmentService = new DepartmentService();
            //可以删除  ，封闭下
            //直接在前端显示
            request.getRequestDispatcher("/dept?method=deptSearch").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //进入部门修改页面，并且通过id 获取相应的部门对象
    public void updataDeptPage(HttpServletRequest request, HttpServletResponse response){
        try {
            String deptId = request.getParameter("deptId");
            //获取对应部门信息
            DepartmentService departmentService = new DepartmentService();
            DepartmentInfo departmentInfo = departmentService.getDeptById(deptId);
            //获取部门的所有负责人，也就是部门表中的一列值。
            List<DepartmentInfo> allManagers = departmentService.getDeptAllManager();
            HttpSession session = request.getSession();
            session.setAttribute("dept",departmentInfo);
            session.setAttribute("managers",allManagers);
            request.getRequestDispatcher("dept/deptUpdate.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //点击修改页面上的保存，进行保存
    public void updateDept(HttpServletRequest request, HttpServletResponse response){
        try {
            String department_id = request.getParameter("department_id");
            String department_name = request.getParameter("department_name");
            String manager = request.getParameter("manager");

            DepartmentInfo departmentInfo = new DepartmentInfo();
            departmentInfo.setDepartment_id(department_id);
            departmentInfo.setDepartment_name(department_name);
            departmentInfo.setManager(manager);
            //更新
            DepartmentService departmentService = new DepartmentService();
            departmentService.updateDept(departmentInfo);
            request.getRequestDispatcher("/dept?method=deptSearch").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //添加部门页面中的   保存按钮请求
    public void addDept(HttpServletRequest request, HttpServletResponse response){
        try {
//            String department_id = request.getParameter("department_id");
            String department_name = request.getParameter("department_name");
            String manager = request.getParameter("manager");

            DepartmentInfo departmentInfo = new DepartmentInfo();
//            departmentInfo.setDepartment_id(department_id);
            departmentInfo.setDepartment_name(department_name);
            departmentInfo.setManager(manager);
            //创建日期，系统生成
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String format = simpleDateFormat.format(date);
            departmentInfo.setCreate_time(format);
            //添加
            DepartmentService departmentService = new DepartmentService();
            departmentService.addDept(departmentInfo);
            request.getRequestDispatcher("/dept?method=deptSearch").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
    * 通过部门名称进行检索
    * */
    public void getDeptByName(HttpServletRequest request, HttpServletResponse response){
        try {
            String department_name = request.getParameter("department_name");
            DepartmentService departmentService = new DepartmentService();
            List<DepartmentInfo> departmentInfos = departmentService.getDeptByName(department_name);
            //能获取到
            System.out.println("222222222222"+departmentInfos);
            request.getSession().setAttribute("depts",departmentInfos);
            request.getRequestDispatcher("/dept?method=deptSearch").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
