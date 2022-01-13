package org.jeecg.modules.demo.LS.service;

import org.jeecg.modules.demo.LS.entity.LsOrderbuyer;
import org.jeecg.modules.demo.LS.entity.LsOrderseller;
import org.jeecg.modules.demo.LS.entity.LsOrderrecord;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
public interface ILsOrderrecordService extends IService<LsOrderrecord> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(LsOrderrecord lsOrderrecord,List<LsOrderbuyer> lsOrderbuyerList,List<LsOrderseller> lsOrdersellerList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(LsOrderrecord lsOrderrecord,List<LsOrderbuyer> lsOrderbuyerList,List<LsOrderseller> lsOrdersellerList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
