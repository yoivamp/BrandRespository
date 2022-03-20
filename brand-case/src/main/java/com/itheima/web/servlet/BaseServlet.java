package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 喵vamp
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求路径
        String uri = req.getRequestURI();
        //2. 获取最后一段路径，方法名
        //路径最后一个/的索引
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);
        //2. 执行方法
        //2.1 获取调用方法对象的字节码 BrandServlet
        Class<? extends BaseServlet> call = this.getClass();
        //2.2 获取方法 Method对象
        try {
            Method method = call.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //2.3调用该方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
