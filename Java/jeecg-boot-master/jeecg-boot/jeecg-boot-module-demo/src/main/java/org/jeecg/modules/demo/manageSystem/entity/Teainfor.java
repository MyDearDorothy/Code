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
 * @Description: 教师信息
 * @Author: jeecg-boot
 * @Date:   2022-03-27
 * @Version: V1.0
 */
@Data
@TableName("teainfor")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="teainfor对象", description="教师信息")
public class Teainfor implements Serializable {
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
	/**教师ID*/
	@Excel(name = "教师ID", width = 15)
    @ApiModelProperty(value = "教师ID")
    private java.lang.String teaId;
	/**教师姓名*/
	@Excel(name = "教师姓名", width = 15)
    @ApiModelProperty(value = "教师姓名")
    private java.lang.String teaName;
	/**教师所属学院*/
	@Excel(name = "教师所属学院", width = 15)
    @ApiModelProperty(value = "教师所属学院")
    private java.lang.String teaDepart;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
    private java.lang.String teaPhone;
	/**电子邮件*/
	@Excel(name = "电子邮件", width = 15)
    @ApiModelProperty(value = "电子邮件")
    private java.lang.String teaEmail;
	/**专业*/
	@Excel(name = "专业", width = 15)
    @ApiModelProperty(value = "专业")
    private java.lang.String teaMajor;
}
