package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * @author 喵vamp
 */
public interface BrandService {
    /**
     * 查询所有数据
     *
     * @return brand列表
     */
    List<Brand> selectAll();

    /**
     * 添加品牌
     *
     * @param brand 品牌信息
     */
    void add(Brand brand);

    /**
     * 根据指定id删除数据
     * @param id 指定id
     */
    void deleteById(int id);

    /**
     * 批量删除
     *
     * @param ids 批量删除的品牌id数组
     */
    void deleteByIds(int[] ids);



    /**
     * 根据用户输入的品牌信息修改数据
     * @param brand 用户输入的brand对象
     */
    void change(Brand brand);

    /**
     * 分页查询
     *页面加载完成后自动调用
     * @param currentPage 当前页码
     * @param pageSize    每页展示条数
     * @return 当前页信息的封装对象
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * 条件查询
     *  开始显式为0，条件查询是从数据库表开头查询，并展示查询结果
     * @param currentPage 开始的索引
     * @param pageSize    每页的条目数
     * @param brand       待筛选的brand对象
     * @return 符合条件的PageBean对象列表
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

}
