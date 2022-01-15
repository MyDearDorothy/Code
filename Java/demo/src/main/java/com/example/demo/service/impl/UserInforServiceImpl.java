package com.example.demo.service.impl;

import com.example.demo.entity.UserInfor;
import com.example.demo.mapper.UserInforMapper;
import com.example.demo.service.IUserInforService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangCheng
 * @since 2022-01-15
 */
@Service
public class UserInforServiceImpl extends ServiceImpl<UserInforMapper, UserInfor> implements IUserInforService {

    @Resource
    public UserInforMapper userInforMapper;

    @Override
    public List<UserInfor> selectUserInfor() {
        List<UserInfor> list=new ArrayList<>();
        list=userInforMapper.selectUserInfor();
        return list;
    }
}
