package org.jeecg.modules.demo.LS.service;

import org.jeecg.modules.demo.LS.entity.LsShopcategory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2021-12-18
 * @Version: V1.0
 */
public interface ILsShopcategoryService extends IService<LsShopcategory> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addLsShopcategory(LsShopcategory lsShopcategory);
	
	/**修改节点*/
	void updateLsShopcategory(LsShopcategory lsShopcategory) throws JeecgBootException;
	
	/**删除节点*/
	void deleteLsShopcategory(String id) throws JeecgBootException;

	/**查询所有数据，无分页*/
    List<LsShopcategory> queryTreeListNoPage(QueryWrapper<LsShopcategory> queryWrapper);

}
