package com.practie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practie.pojo.Brand;
import com.practie.service.IBrandService;
import com.practie.utils.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author 喵vamp
 */
@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private IBrandService iBrandService;

    @PostMapping
    public Result save(HttpServletResponse resp, @RequestBody Brand brand) {
        return new Result(resp.getStatus(), iBrandService.save(brand));
    }

    @DeleteMapping("{id}")
    public Result deleteById(HttpServletResponse resp, @PathVariable("id") Long[] id) {
        return new Result(resp.getStatus(), iBrandService.removeByIds(Arrays.asList(id)));
    }


    @PutMapping
    public Result update(HttpServletResponse resp, @RequestBody Brand brand) {
        return new Result(resp.getStatus(), iBrandService.updateById(brand));
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result getAll(HttpServletResponse resp, @PathVariable Integer currentPage, @PathVariable Integer pageSize, Brand brand) {
        //设置查询条件
        LambdaQueryWrapper<Brand> lam = new LambdaQueryWrapper<>();
        lam.like(Strings.isNotEmpty(brand.getBrandName()), Brand::getBrandName, brand.getBrandName());
        lam.like(Strings.isNotEmpty(brand.getCompanyName()), Brand::getCompanyName, brand.getCompanyName());
        if (brand.getStatus() != null) {
            lam.eq(Brand::getStatus, brand.getStatus());
        }

        //进行查询
        Page<Brand> page = iBrandService.page(new Page<>(currentPage, pageSize), lam);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (currentPage > page.getPages()) {
            page = iBrandService.page(new Page<>(page.getPages(), pageSize), lam);
        }
        return new Result(resp.getStatus(), page.getRecords(), page.getTotal());
    }

}
