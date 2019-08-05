package com.feng.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/BaseServlet")
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String methodName = request.getParameter("method");
        System.out.println(methodName);
        if (methodName==null || methodName.length()==0){
            methodName="execute";
        }

//        反射调用相应的方法
        //这个this，谁调用就指的时谁。
        try {
            Class cla = this.getClass();
            Method method = cla.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
