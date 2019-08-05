package com.feng.controller;

import com.feng.bean.DepartmentInfo;
import com.feng.bean.PageInfoBean;
import com.feng.bean.UserInfo;
import com.feng.service.DepartmentService;
import com.feng.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/main")
public class UserServlet extends BaseServlet {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("用户没有指定调用的具体方法");
        request.getRequestDispatcher("error.jsp").forward(request,response);

    }
    /*
    * 用户登录  根据权限分别进入各自的页面
    * */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String  name = (String) request.getParameter("name");
        String password = (String) request.getParameter("password");

        //调用方法
        UserService userService = new UserService();
        UserInfo userInfo = userService.userLogin(name, password);

        if (userInfo==null || "".equals(userInfo)){
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }else if ("0".equals(String.valueOf(userInfo.getMylevel()))){
            //超级管理员     将登陆的 用户保存到session中 便于 个人信息修改模块
            HttpSession session = request.getSession();
            session.setAttribute("user",userInfo);
            request.getRequestDispatcher("superManager.jsp").forward(request,response);
        }else if ("1".equals(String.valueOf(userInfo.getMylevel()))){
            //经理        将登陆的 用户保存到session中 便于 个人信息修改模块
            HttpSession session = request.getSession();
            session.setAttribute("user",userInfo);
            request.getRequestDispatcher("director.jsp").forward(request,response);

        }else if ("2".equals(String.valueOf(userInfo.getMylevel()))){
            //普通员工      将登陆的 用户保存到session中 便于 个人信息修改模块
            HttpSession session = request.getSession();
            session.setAttribute("user",userInfo);
            request.getRequestDispatcher("employee.jsp").forward(request,response);

        }

    }


    /*
    * 进入用户管理界面
    * 并获取所有的用户信息
    * 并获取所有的部门信息
    * */
    public void  userSearch(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取所有部门
            DepartmentService departmentService = new DepartmentService();
            List<DepartmentInfo> departments = departmentService.getAllDepartment();

            //传入两个值，，当前页，和页大小
            int pageRecord = 5; //页的记录数
            String pageNum1 = request.getParameter("pageNum");
            if (pageNum1 == null){
                pageNum1 = "1";
            }
            Integer pageNum = Integer.valueOf(pageNum1);   //当前页

            //获取所有用户
            UserService userService = new UserService();
            //分页
            PageHelper.startPage(pageNum,pageRecord);//  参数一：当前页  参数二：页的大小，一页的记录数
            List<UserInfo> userInfos = userService.userSearch();
            //传入要连续显示多少页
            PageInfo<UserInfo> info = new PageInfo<>(userInfos,5);        //这样也可以

            PageInfoBean pageInfoBean = new PageInfoBean();

            int pageNum2 = info.getPageNum();           //  当前页
            long total = info.getTotal();               //  总记录数
            int pageSize = info.getPageSize();          //  一页的大小
            int pages = info.getPages();                //  总页码
            boolean isFirstPage = info.isIsFirstPage();
            boolean isLastPage = info.isIsLastPage();
            int navigateFirstPage = info.getNavigateFirstPage();
            int navigateLastPage = info.getNavigateLastPage();

            System.out.println("first"+navigateFirstPage);
            System.out.println("last"+navigateLastPage);

            pageInfoBean.setPageNum(pageNum2);              //  当前页面
            pageInfoBean.setList(userInfos);                //  页大小的集合
            pageInfoBean.setTotalRecord((int) total);       //   所有记录
            pageInfoBean.setTotalPage(pages);               //  获取所有的页

            pageInfoBean.setFirstPageNum(navigateFirstPage);//  分页的第一个页面的页号
            pageInfoBean.setLastPageNum(navigateLastPage);  //  分页的最后一个页面的页号

            //设置
            HttpSession session = request.getSession();
            session.setAttribute("user",userInfos);//之前的        //自动进行分好页
            session.setAttribute("pageInfoBean",pageInfoBean);      // 页码信息
            session.setAttribute("department",departments);         //部门信息
            //进入页面
            System.out.println("pageInfoBean"+pageInfoBean);
            request.getRequestDispatcher("user/userSearch.jsp").forward(request,response);



        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*UserService userService = new UserService();
        List<UserInfo> userInfos = userService.userSearch();

        if (userInfos != null){
            int totalRecord = userInfos.size(); //获取总页数
            //以分页显示的形式 查询所有用户
            //获取当前页数
            String pageNumStr = request.getParameter("pageNum");
            if (pageNumStr == null || "".equals(pageNumStr)){
                pageNumStr ="1";
            }
            Integer pageNum = Integer.valueOf(pageNumStr);
            //设置每页显示的记录数
            int pageRecord = 5 ;
            //以每页显示的记录数去分页实现查询用户
            PageInfoBean pi = userService.getAllUserByPageInfo(pageNum,pageRecord,totalRecord);
            if (pi.getList()!=null){
                try {
                    request.setAttribute("pi",pi);
                    request.getRequestDispatcher("user/userSearch.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                request.setAttribute("","");
            }

        }*/








    }

    /*
    * 进入修改页面
    * */
    public void updateUserPage(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String department_id = request.getParameter("department_id");
        //根据id获取用户
        UserService userService = new UserService();
        UserInfo userById = userService.getUserById(Integer.valueOf(id));

        //前端获取部门  通过上一个界面的session
        HttpSession session = request.getSession();
        session.setAttribute("user",userById);
        try {
            request.getRequestDispatcher("user/userUpdate.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * 进入修改页面之后  这是保存的请求
    * */
    public void updateUser(HttpServletRequest request, HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
//            if (password.equals(password2)){
//
//            }
            System.out.println("111111111111111111111111111111");
            String name = request.getParameter("name");
            String department_id = request.getParameter("department_name");//下拉框
            String sex = request.getParameter("sex");               //单选框
            String mobile = request.getParameter("mobile");
            String birthday = request.getParameter("birthday");     //date类型
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
                Date date = simpleDateFormat.parse(birthday);
            String email = request.getParameter("email");

            UserInfo userInfo = new UserInfo();
            userInfo.setAccount(account);
            userInfo.setPassword(password);
            userInfo.setName(name);
            userInfo.setDepartment_id(department_id);
            userInfo.setSex(sex);
            userInfo.setMobile(mobile);
            userInfo.setBirthday(date);
            userInfo.setEmail(email);

            //更新用户
            UserService userService = new UserService();
            userService.updateUser(userInfo);

            //发送请求。
            request.getRequestDispatcher("/main?method=userSearch").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /*
    * 这是进入的添加用户页面的请求
    * */
    public void userInsertPage(HttpServletRequest request,HttpServletResponse response){
        try {
            //进入页面
            request.getRequestDispatcher("user/userInsert.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    * 点击保存之后的请求
    * 保存用户
    * */
    public void userInsert(HttpServletRequest request,HttpServletResponse response){

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
//        if (password.equals(password1)){
//            
//        }
        String name = request.getParameter("name");
        String department_name = request.getParameter("department_name");
        String sex = request.getParameter("sex");
        String mobile = request.getParameter("mobile");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");

        Date date = null;
        try {
            date = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String email = request.getParameter("email");
        UserInfo user = new UserInfo();

        user.setAccount(account);
        user.setName(name);
        user.setPassword(password);
        user.setDepartment_name(department_name);
        user.setSex(sex);
        user.setMobile(mobile);
        user.setBirthday(date);
        user.setEmail(email);
            Date createTime = new Date();
        user.setCreate_time(createTime);
        //插入
        UserService userService = new UserService();
        userService.addUser(user);
        try {
            //发送请求
            request.getRequestDispatcher("/main?method=userSearch").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据姓名和部门联合查询
     * @param request
     * @param response
     */
    public void getUserByNameAndDept(HttpServletRequest request,HttpServletResponse response){
        String name = request.getParameter("name");
        String department_name = request.getParameter("department_name");

        System.out.println("param"+name+department_name);

        UserService userService = new UserService();
        //获取返回的用户集合
        List<UserInfo> userInfos = new ArrayList<>();
        userInfos = userService.getUserByNameAndDept(name, department_name);

        System.out.println("getUserByNameAndDept"+userInfos.toString());
        //封装到session
        request.getSession().setAttribute("user",userInfos);
        try {
            response.sendRedirect("/main?method=userSearch");
//            request.getRequestDispatcher("/main?method=userSearch").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*
    * 复选框删除多用户
    * */
    public void deleteManyUserByAccount(HttpServletRequest request,HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            System.out.println("deleteManyUser::"+account);

            UserService userService = new UserService();

            String[] split = account.split(",");
            for (String a : split){
                userService.deleteUserByAccount(a);
            }

            request.getRequestDispatcher("/main?method=userSearch").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //删除 单个用户 通过account
    public void deleteUserByAccount(HttpServletRequest request,HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            System.out.println("要删除的account值：："+account);
            UserService userService = new UserService();
            userService.deleteUserByAccount(account);
            request.getRequestDispatcher("/main?method=userSearch").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
