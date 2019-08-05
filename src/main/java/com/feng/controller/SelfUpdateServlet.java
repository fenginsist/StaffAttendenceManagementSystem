package com.feng.controller;

import com.feng.bean.DepartmentInfo;
import com.feng.bean.UserInfo;
import com.feng.service.DepartmentService;
import com.feng.service.SelfUpdateService;
import com.feng.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/selfUpdate")
public class SelfUpdateServlet extends BaseServlet {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("用户没有指定调用的具体方法");
        request.getRequestDispatcher("error.jsp").forward(request,response);

    }

    //去个人修改的页面，并且把部门查出来       success
    public void toUpdateSelfPage(HttpServletRequest request, HttpServletResponse response){
        try {

            //这里回显的数据不能用登录时的用户对象来回显，因为修改后 回到本页面还是登录时保存到session中旧数据，
            //所以这里需要从新查询数据，因为account不能修改，所以可以用 登录时session中的account来获取新值
            HttpSession session = request.getSession();
            UserInfo user = (UserInfo) session.getAttribute("user");
            String account = user.getAccount();

            SelfUpdateService selfUpdateService = new SelfUpdateService();
            UserInfo userInfo = selfUpdateService.getUserByAccount(account);

            //存入部门信息   但是前端不让修改，已经写死
//            DepartmentService departmentService = new DepartmentService();
//            List<DepartmentInfo> allDepartment = departmentService.getAllDepartment();
//            System.out.println(allDepartment.toString());
//
//            session.setAttribute("depts",allDepartment);
            session.setAttribute("newUser",userInfo);

            request.getRequestDispatcher("user/userPasswordUpdate.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    * 修改本人信息            success
    * */
    public void updateSelf(HttpServletRequest request, HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String password1 = request.getParameter("password1");
            String name = request.getParameter("name");
            String department_name = request.getParameter("department_name");
            String sex = request.getParameter("sex");
            String mobile = request.getParameter("mobile");
            String birthday = request.getParameter("birthday");
            Date parse = null;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                parse = simpleDateFormat.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String email = request.getParameter("email");

            UserInfo userInfo = new UserInfo();
            userInfo.setAccount(account);
            userInfo.setPassword(password);
            userInfo.setName(name);
            userInfo.setDepartment_name(department_name);
            userInfo.setSex(sex);
            userInfo.setMobile(mobile);
            userInfo.setBirthday(parse);
            userInfo.setEmail(email);

            UserService userService = new UserService();
            userService.updateUser(userInfo);
            request.getRequestDispatcher("/selfUpdate?method=toUpdateSelfPage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
