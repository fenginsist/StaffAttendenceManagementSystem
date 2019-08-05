package com.feng.controller;

import com.feng.bean.RestInfo;
import com.feng.service.RestService;

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

@WebServlet("/rest")
public class RestServlet extends BaseServlet {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("用户没有指定调用的具体方法");
        request.getRequestDispatcher("error.jsp").forward(request,response);
    }

    public void toRestSearch(HttpServletRequest request, HttpServletResponse response){
        try {
            RestService restService = new RestService();
            List<RestInfo> restInfos = restService.getAllRest();

            System.out.println("111111111"+restInfos);

            request.getSession().setAttribute("rests",restInfos);

            request.getRequestDispatcher("restmanager/restSearch.jsp").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除单个          success
    public void deleteRest(HttpServletRequest request, HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            RestService restService = new RestService();

            restService.deleteRestByAccount(account);
            request.getRequestDispatcher("").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 到修改 页面  带着 数据  并回显           false
    public void toUpdateRestPage(HttpServletRequest request, HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            RestService restService = new RestService();
            RestInfo restInfo = restService.getRestByAccount(account);
            System.out.println("@@@@"+restInfo.toString());
            request.getSession().setAttribute("rest",restInfo);

            request.getRequestDispatcher("restmanager/restUpdate.jsp").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //保存            success
    public void updateRest(HttpServletRequest request, HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            String name = request.getParameter("name");
            String rest_start_date = request.getParameter("rest_start_date");
            String start_time = request.getParameter("start_time");
            String rest_end_date = request.getParameter("rest_end_date");
            String end_time = request.getParameter("end_time");
            String rest_time = request.getParameter("rest_time");
            String rest_cause = request.getParameter("rest_cause");

            RestInfo restInfo = new RestInfo();
            restInfo.setAccount(account);
            restInfo.setName(name);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date rest_start_date1 = simpleDateFormat.parse(rest_start_date);
                Date start_time1 = simpleDateFormat.parse(start_time);
                Date rest_end_date1 = simpleDateFormat.parse(rest_end_date);
                Date end_time1 = simpleDateFormat.parse(end_time);
                Date rest_time1 = simpleDateFormat.parse(rest_time);

                restInfo.setRest_start_date(rest_start_date1);
                restInfo.setStart_time(start_time1);
                restInfo.setRest_end_date(rest_end_date1);
                restInfo.setEnd_time(end_time1);
                restInfo.setRest_time(rest_time1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            restInfo.setRest_cause(rest_cause);

            RestService restService = new RestService();
            restService.updateRest(restInfo);
            request.getRequestDispatcher("restmanager/restSearch.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //删除多个   success
    public void deleteManyRest(HttpServletRequest request, HttpServletResponse response){
        try {
            String account = request.getParameter("account");
            System.out.println("111"+account);
            String[] split = account.split(",");
            for (String a : split){
                RestService restService = new RestService();
                restService.deleteRestByAccount(a);
            }
            request.getRequestDispatcher("/rest?method=toRestSearch").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //添加数据          success
    public void addRest(HttpServletRequest request, HttpServletResponse response){

        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String rest_start_date = request.getParameter("rest_start_date");
        String start_time = request.getParameter("start_time");
        String rest_end_date = request.getParameter("rest_end_date");
        String end_time = request.getParameter("end_time");
        String rest_time = request.getParameter("rest_time");
        String rest_cause = request.getParameter("rest_cause");

        RestInfo restInfo = new RestInfo();
        restInfo.setAccount(account);
        restInfo.setName(name);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date rest_start_date1 = simpleDateFormat.parse(rest_start_date);
            Date start_time1 = simpleDateFormat.parse(start_time);
            Date rest_end_date1 = simpleDateFormat.parse(rest_end_date);
            Date end_time1 = simpleDateFormat.parse(end_time);
            Date rest_time1 = simpleDateFormat.parse(rest_time);

            restInfo.setRest_start_date(rest_start_date1);
            restInfo.setStart_time(start_time1);
            restInfo.setRest_end_date(rest_end_date1);
            restInfo.setEnd_time(end_time1);
            restInfo.setRest_time(rest_time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        restInfo.setRest_cause(rest_cause);

        RestService restService = new RestService();

        System.out.println("1111"+restInfo.toString());
        restService.addRest(restInfo);
        try {
            request.getRequestDispatcher("/rest?method=toRestSearch").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
