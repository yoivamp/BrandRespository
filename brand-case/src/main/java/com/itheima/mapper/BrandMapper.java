package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 喵vamp
 */
public interface BrandMapper {
    /**
     * 查询所有数据
     *
     * @return brand列表
     */
    @Select("select * from tb_brand;")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();


    /**
     * 添加品牌
     *
     * @param brand 品牌信息
     */
    @Insert("insert into tb_brand  values (null,#{brandName},#{companyName},#{ordered},#{description},#{status});")
    @ResultMap("brandResultMap")
    void add(Brand brand);

    /**
     * 根据指定id删除数据
     * @param id 指定id
     */
    @Delete("delete from tb_brand where id=#{id};")
    void deleteById(int id);

    /**
     * 批量删除
     *
     * @param ids 批量删除的品牌id数组
     */
    void delectByIds(@Param("ids") int[] ids);


    /**
     * 根据用户输入的品牌信息修改数据
     * @param brand 用户输入的brand对象
     */
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id} ;")
    @ResultMap("brandResultMap")
    void change(Brand brand);

    /**
     * 分页查询
     *
     * @param begin 当前页的索引
     * @param size  每页的条目数
     * @return 品牌数据
     */
    @Select("select * from tb_brand limit #{begin},#{size};")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询总记录数
     *
     * @return 总记录数
     */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    /**
     * 条件并分页查询
     *
     * @param begin 开始的索引
     * @param size  每页的条目数
     * @param brand 设置条件的brand对象
     * @return 符合条件的brand列表
     */
    @ResultMap("brandResultMap")
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand") Brand brand);

    /**
     * 查询满足条件的总记录数
     *
     * @param brand 设置条件的brand对象
     * @return 符合条件的总记录数
     */
    @ResultMap("brandResultMap")
    int selectTotalCountByCondition(Brand brand);
}
