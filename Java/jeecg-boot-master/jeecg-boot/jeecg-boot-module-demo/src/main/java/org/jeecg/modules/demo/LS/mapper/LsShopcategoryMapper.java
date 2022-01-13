package org.jeecg.modules.demo.LS.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.LS.entity.LsShopcategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商品分类
 * @Author: jeecg-boot
 * @Date:   2021-12-18
 * @Version: V1.0
 */
public interface LsShopcategoryMapper extends BaseMapper<LsShopcategory> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

}
