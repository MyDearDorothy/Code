package com.example.demo.controller;


import com.example.demo.entity.UserInfor;
import com.example.demo.service.impl.UserInforServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangCheng
 * @since 2022-01-15
 */
@RestController                //相当于@Controller和@ResponseBody,等于在每个方法前加注解@ResponseBody
public class UserInforController {
    @Autowired
    private UserInforServiceImpl userInforService;

    @RequestMapping("/userInfor")
    public List<UserInfor> selectUserInfor(){
        List<UserInfor> list=userInforService.selectUserInfor();
        return list;
    }
}
