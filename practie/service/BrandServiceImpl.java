package com.practie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practie.dao.BrandMapper;
import com.practie.pojo.Brand;
import org.springframework.stereotype.Service;

/**
 * @author 喵vamp
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

}
