package org.jeecg.modules.demo.LS.entity;

import java.io.Serializable;
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
import java.io.UnsupportedEncodingException;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2021-12-18
 * @Version: V1.0
 */
@Data
@TableName("ls_shopcategory")
@ApiModel(value="ls_shopcategory对象", description="商品分类")
public class LsShopcategory implements Serializable {
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
	/**分类名*/
	@Excel(name = "分类名", width = 15)
    @ApiModelProperty(value = "分类名")
    private java.lang.String name;
	/**分类描述*/
	@Excel(name = "分类描述", width = 15)
    @ApiModelProperty(value = "分类描述")
    private java.lang.String description;
	/**分类图片*/
	@Excel(name = "分类图片", width = 15)
    @ApiModelProperty(value = "分类图片")
    private java.lang.String pic;
	/**父级节点*/
	@Excel(name = "父级节点", width = 15, dictTable = "ls_shopcategory", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ls_shopcategory", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "父级节点")
    private java.lang.String pid;
	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @ApiModelProperty(value = "是否有子节点")
    private java.lang.String hasChild;
}
