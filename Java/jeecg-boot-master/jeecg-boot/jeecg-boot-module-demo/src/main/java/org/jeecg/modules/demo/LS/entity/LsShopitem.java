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
 * @Description: 商品信息
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("ls_shopitem")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ls_shopitem对象", description="商品信息")
public class LsShopitem implements Serializable {
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
	/**商品名*/
	@Excel(name = "商品名", width = 15)
    @ApiModelProperty(value = "商品名")
    private java.lang.String name;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String description;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private java.lang.String pic;
	/**价格*/
	@Excel(name = "价格", width = 15)
    @ApiModelProperty(value = "价格")
    private java.lang.Double price;
	/**库存量*/
	@Excel(name = "库存量", width = 15)
    @ApiModelProperty(value = "库存量")
    private java.lang.Double stock;
	/**单位*/
	@Excel(name = "单位", width = 15, dicCode = "unit")
	@Dict(dicCode = "unit")
    @ApiModelProperty(value = "单位")
    private java.lang.String unit;
	/**类别*/
	@Excel(name = "类别", width = 15, dictTable = "ls_shopcategory", dicText = "name", dicCode = "id")
	@Dict(dictTable = "ls_shopcategory", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "类别")
    private java.lang.String category;
}
