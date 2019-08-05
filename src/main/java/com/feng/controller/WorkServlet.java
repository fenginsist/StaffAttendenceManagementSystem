package com.feng.controller;

import com.feng.bean.WorkInfo;
import com.feng.service.WorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/work")
public class WorkServlet extends BaseServlet {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("用户没有指定调用的具体方法");
        request.getRequestDispatcher("error.jsp").forward(request,response);
    }


    public void toWorkSearchPage(HttpServletRequest request, HttpServletResponse response){
        try {
            WorkService workService = new WorkService();
            List<WorkInfo> workInfos = workService.getAllWork();
            request.getSession().setAttribute("works",workInfos);
            request.getRequestDispatcher("worksInspect/worksInspect.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 审核不通过  删除            success
    public void deleteWorkInspect(HttpServletRequest request, HttpServletResponse response){

        try {
            String account = request.getParameter("account");
            System.out.println("deleteWorkInspect"+account);
            WorkService workService = new WorkService();
            workService.deleteWorkInsepectByAccount(account);
            request.getRequestDispatcher("worksInspect/worksInspect.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 审核通过
    public void inspectSuccess(HttpServletRequest request, HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            System.out.println("inspectSuccess"+account);

            request.getRequestDispatcher("worksInspect/worksInspect.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
