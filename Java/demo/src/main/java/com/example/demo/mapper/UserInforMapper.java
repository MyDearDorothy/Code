package com.example.demo.mapper;

import com.example.demo.entity.UserInfor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZhangCheng
 * @since 2022-01-15
 */
public interface UserInforMapper extends BaseMapper<UserInfor> {
    @Select("select * from user_Infor")
    public List<UserInfor> selectUserInfor();

}
