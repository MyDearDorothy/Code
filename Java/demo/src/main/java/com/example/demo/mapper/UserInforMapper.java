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
//@Repository             //每个mapper类前加上@Repository注解,解决了Service层使用Mapper对象出错问题
public interface UserInforMapper extends BaseMapper<UserInfor> {
    @Select("select * from user_Infor")
    public List<UserInfor> selectUserInfor();

}
