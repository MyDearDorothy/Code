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
 * @Description: 课程信息
 * @Author: jeecg-boot
 * @Date:   2022-03-27
 * @Version: V1.0
 */
@Data
@TableName("courseinfor")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="courseinfor对象", description="课程信息")
public class Courseinfor implements Serializable {
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
	/**课程编号*/
	@Excel(name = "课程编号", width = 15)
    @ApiModelProperty(value = "课程编号")
    private java.lang.String courseId;
	/**课程名称*/
	@Excel(name = "课程名称", width = 15)
    @ApiModelProperty(value = "课程名称")
    private java.lang.String courseName;
	/**上课地点*/
	@Excel(name = "上课地点", width = 15)
    @ApiModelProperty(value = "上课地点")
    private java.lang.String courseLocation;
	/**上课教师ID*/
	@Excel(name = "上课教师ID", width = 15)
    @ApiModelProperty(value = "上课教师ID")
    private java.lang.String courseTeaId;
	/**上课最大学生数目*/
	@Excel(name = "上课最大学生数目", width = 15)
    @ApiModelProperty(value = "上课最大学生数目")
    private java.lang.Integer courseNum;
	/**开课学院*/
	@Excel(name = "开课学院", width = 15)
    @ApiModelProperty(value = "开课学院")
    private java.lang.String courseDepart;
	/**课程可选数目*/
	@Excel(name = "课程可选数目", width = 15)
    @ApiModelProperty(value = "课程可选数目")
    private java.lang.Integer courseLeft;
}
