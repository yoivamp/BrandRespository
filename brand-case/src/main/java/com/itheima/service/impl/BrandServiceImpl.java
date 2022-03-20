package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author 喵vamp
 */
public class BrandServiceImpl implements BrandService {
    /**
     * 1. 创建SqlSessionFactory 工厂对象
     */
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有数据
     *
     * @return brand列表
     */
    @Override
    public List<Brand> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands = mapper.selectAll();
        //5.释放资源
        sqlSession.close();
        return brands;
    }

    /**
     * 添加品牌
     *
     * @param brand 品牌信息
     */
    @Override
    public void add(Brand brand) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession(true);
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.add(brand);
        //5.释放资源
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession(true);
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.close();
    }

    /**
     * 删除对应id数组的品牌
     *
     * @param ids 批量删除的品牌id数组
     */
    @Override
    public void deleteByIds(int[] ids) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession(true);
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.delectByIds(ids);
        System.out.println("ok");
        //5.释放资源
        sqlSession.close();
    }


    /**
     * 根据用户输入的品牌信息修改数据
     *
     * @param brand 用户输入的brand对象
     */
    @Override
    public void change(Brand brand) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession(true);
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.change(brand);
        sqlSession.close();
    }

    /**
     * 分页查询
     *
     * @param currentPage 当前页码
     * @param pageSize    每页展示条数
     * @return 封装的pagebean对象
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //5. 查询当前页数据
        List<Brand> brands = mapper.selectByPage(begin, pageSize);
        //6. 查询总记录数
        int count = mapper.selectTotalCount();
        //7. 封装PageBean对象
        PageBean<Brand> brandPageBean = new PageBean<>(count, brands);
        //8. 释放资源
        sqlSession.close();
        return brandPageBean;
    }

    /**
     * 条件并分页查询
     *
     * @param currentPage 开始的索引
     * @param pageSize    每页的条目数
     * @param brand       设置条件的brand对象
     * @return 符合条件的pagebean对象
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println("查询索引   " + "   " + pageSize);

        // 4.处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }
        System.out.println("处理后数据   " + brand);

        //5. 查询当前页数据
        List<Brand> brands = mapper.selectByPageAndCondition(currentPage, pageSize, brand);
        System.out.println("当前页数据   " + brands);
        //6. 查询总记录数
        int count = mapper.selectTotalCountByCondition(brand);
        //7. 封装PageBean对象
        PageBean<Brand> brandPageBean = new PageBean<>(count, brands);
        System.out.println(brandPageBean);
        //8. 释放资源
        sqlSession.close();
        return brandPageBean;
    }
}
