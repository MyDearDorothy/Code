package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangCheng
 * @since 2022-01-11
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
