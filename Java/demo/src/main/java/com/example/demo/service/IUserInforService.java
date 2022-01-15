package com.example.demo.service;

import com.example.demo.entity.UserInfor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangCheng
 * @since 2022-01-15
 */
public interface IUserInforService extends IService<UserInfor> {
    public List<UserInfor> selectUserInfor();
}
