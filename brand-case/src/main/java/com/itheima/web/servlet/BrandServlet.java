package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author 喵vamp
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private final BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service查询
        List<Brand> brands = brandService.selectAll();
        //转换为JSON数据
        String jsonString = JSON.toJSONString(brands);
        //响应页面
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收品牌数据
        request.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = request.getReader();
        //json字符串
        String params = bufferedReader.readLine();
        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //2. 调用service添加
        brandService.add(brand);
        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收批量删除的品牌id数组的json数据
        BufferedReader bufferedReader = request.getReader();
        //json字符串
        String params = bufferedReader.readLine();
        System.out.println(params);
        //转为int[]对象
        int id = JSON.parseObject(params, int.class);
        System.out.println(id);
        //2. 调用service删除
        brandService.deleteById(id);
        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收批量删除的品牌id数组的json数据
        BufferedReader bufferedReader = request.getReader();
        //json字符串
        String params = bufferedReader.readLine();
        System.out.println(params);
        //转为int[]对象
        int[] ids = JSON.parseObject(params, int[].class);
        System.out.println(ids);
        //2. 调用service删除
        brandService.deleteByIds(ids);
        //3. 响应成功的标识
        response.getWriter().write("success");
    }


    public void change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收用户输入的brand对象
        request.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = request.getReader();
        //json字符串
        String params = bufferedReader.readLine();
        System.out.println(params);
        //转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        System.out.println(brand);
        //2. 调用service修改
        brandService.change(brand);
        //3. 响应成功的标识
        response.getWriter().write("success");
    }


    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收当前页码和每页展示条数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //调用service查询
        PageBean<Brand> brandPageBean = brandService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        //转换为JSON数据
        String jsonString = JSON.toJSONString(brandPageBean);
        //响应页面
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收当前页数和每页展示条数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        System.out.println("前端数据   " + "   " + pageSize);
        // 2.获取查询条件对象
        request.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = request.getReader();
        //json字符串
        String params = bufferedReader.readLine();
        //转换brand
        Brand brand = JSON.parseObject(params, Brand.class);
        System.out.println("条件brand    " + brand);
        //3.调用service查询
        //计算索引
        int size = Integer.parseInt(pageSize);
        int begin = (Integer.parseInt(currentPage) - 1) * size;
        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(begin, size, brand);
        //转换为JSON数据
        System.out.println("待响应json   " + brandPageBean);
        String jsonString = JSON.toJSONString(brandPageBean);
        //4.响应页面
        //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
