package com.feng.controller;

import com.feng.bean.ReportInfo;
import com.feng.service.ReportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/report")
public class ReportServlet extends BaseServlet {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("用户没有指定调用的具体方法");
        request.getRequestDispatcher("error.jsp").forward(request,response);
    }

    //跳到日报页面，并获取所有日报信息，遍历       success
    public void toReportSearchPage(HttpServletRequest request, HttpServletResponse response){
        try {
            //获取所有日报
            ReportService reportService = new ReportService();
            List<ReportInfo> reportInfos = reportService.getAllReport();
            request.getSession().setAttribute("reports",reportInfos);
            request.getRequestDispatcher("report/reportSearch.jsp").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //去更新 日报的页面，并携带其数据          success
    public void toUpdateReportPage(HttpServletRequest request, HttpServletResponse response){
        try {
            String reportAccount = request.getParameter("reportAccount");
            System.out.println(reportAccount);
            ReportService reportService = new ReportService();
            ReportInfo reportInfo = reportService.getReportByAccount(reportAccount);
            request.getSession().setAttribute("report",reportInfo);
            request.getRequestDispatcher("report/reportUpdate.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //在更改日报页面，点击保存日报        success
    public void updateReport(HttpServletRequest request, HttpServletResponse response){

        String account = request.getParameter("account");
        String report_date = request.getParameter("report_date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = new Date();
        try {
            parse = simpleDateFormat.parse(report_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String work_process = request.getParameter("work_process");
        String work_content = request.getParameter("work_content");
        String tomorrow_plan = request.getParameter("tomorrow_plan");
        String problem = request.getParameter("problem");
        String other = request.getParameter("other");
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setAccount(account);
        reportInfo.setReport_date(parse);
        reportInfo.setWork_process(work_process);
        reportInfo.setWork_content(work_content);
        reportInfo.setTomorrow_plan(tomorrow_plan);
        reportInfo.setProblem(problem);
        reportInfo.setOther(other);

        ReportService reportService = new ReportService();
        reportService.updateReportByAccount(reportInfo);

        try {
            request.getRequestDispatcher("/report?method=toReportSearchPage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 点击删除  单个日报   success
    public void deleteReportByAccount(HttpServletRequest request, HttpServletResponse response){
        String account = request.getParameter("reportAccount");
        System.out.println("deleteReportByAccount"+account);
        ReportService reportService = new ReportService();
        reportService.deleteReportByAccount(account);
        try {
            request.getRequestDispatcher("/report?method=toReportSearchPage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //去添加日报的页面          success
    public void addReport(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String report_date = request.getParameter("report_date");
        String work_process = request.getParameter("work_process");
        String work_content = request.getParameter("work_content");
        String tomorrow_plan = request.getParameter("tomorrow_plan");
        String problem = request.getParameter("problem");
        String other = request.getParameter("other");

        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setName(name);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = new Date();
        try {
            parse = simpleDateFormat.parse(report_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reportInfo.setReport_date(parse);
        reportInfo.setWork_process(work_process);
        reportInfo.setWork_content(work_content);
        reportInfo.setTomorrow_plan(tomorrow_plan);
        reportInfo.setProblem(problem);
        reportInfo.setOther(other);

        ReportService reportService = new ReportService();
        reportService.addReport(reportInfo);
        System.out.println("1111111111111"+reportInfo);
        try {
            request.getRequestDispatcher("/report?method=toReportSearchPage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //批量删除多个日报      success
    public void deleteManyReport(HttpServletRequest request, HttpServletResponse response){
        String account = request.getParameter("account");
        System.out.println("account::"+account);
        String[] split = account.split(",");
        ReportService reportService = new ReportService();
        for (String a : split){
            reportService.deleteReportByAccount(a);
        }
        try {
            request.getRequestDispatcher("/report?method=toReportSearchPage").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
