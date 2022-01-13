package org.jeecg.modules.demo.LS.entity;

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
 * @Description: 买家信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("ls_buyer")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ls_buyer对象", description="买家信息表")
public class LsBuyer implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private java.lang.String buyerUsername;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
    private java.lang.String buyerPassword;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
    private java.lang.String telephone;
	/**用户头像*/
	@Excel(name = "用户头像", width = 15)
    @ApiModelProperty(value = "用户头像")
    private java.lang.String displayPic;
	/**性别*/
	@Excel(name = "性别", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private java.lang.String sex;
	/**其他*/
	@Excel(name = "其他", width = 15)
    @ApiModelProperty(value = "其他")
    private java.lang.String other;
}
