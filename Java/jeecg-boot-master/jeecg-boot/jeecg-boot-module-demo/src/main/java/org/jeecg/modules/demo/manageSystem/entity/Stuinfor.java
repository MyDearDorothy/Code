package org.jeecg.modules.demo.manageSystem.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 学生信息
 * @Author: jeecg-boot
 * @Date:   2022-03-26
 * @Version: V1.0
 */
@Data
@TableName("stuinfor")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="stuinfor对象", description="学生信息")
public class Stuinfor implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**学生ID*/
	@Excel(name = "学生ID", width = 15)
    @ApiModelProperty(value = "学生ID")
    private java.lang.String stuId;
	/**学生姓名*/
	@Excel(name = "学生姓名", width = 15)
    @ApiModelProperty(value = "学生姓名")
    private java.lang.String stuName;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private java.util.Date stuBirth;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private java.lang.String stuSex;
	/**学院*/
	@Excel(name = "学院", width = 15)
    @ApiModelProperty(value = "学院")
    private java.lang.String stuCollege;
	/**专业*/
	@Excel(name = "专业", width = 15)
    @ApiModelProperty(value = "专业")
    private java.lang.String stuMajor;
	/**班级*/
	@Excel(name = "班级", width = 15)
    @ApiModelProperty(value = "班级")
    private java.lang.String stuClass;
	/**家庭地址*/
	@Excel(name = "家庭地址", width = 15)
    @ApiModelProperty(value = "家庭地址")
    private java.lang.String stuAddress;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
    private java.lang.String stuPhone;
}
