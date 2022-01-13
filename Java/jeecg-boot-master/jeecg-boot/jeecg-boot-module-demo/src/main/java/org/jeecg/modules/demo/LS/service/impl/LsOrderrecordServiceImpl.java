package org.jeecg.modules.demo.LS.service.impl;

import org.jeecg.modules.demo.LS.entity.LsOrderrecord;
import org.jeecg.modules.demo.LS.entity.LsOrderbuyer;
import org.jeecg.modules.demo.LS.entity.LsOrderseller;
import org.jeecg.modules.demo.LS.mapper.LsOrderbuyerMapper;
import org.jeecg.modules.demo.LS.mapper.LsOrdersellerMapper;
import org.jeecg.modules.demo.LS.mapper.LsOrderrecordMapper;
import org.jeecg.modules.demo.LS.service.ILsOrderrecordService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 订单信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Service
public class LsOrderrecordServiceImpl extends ServiceImpl<LsOrderrecordMapper, LsOrderrecord> implements ILsOrderrecordService {

	@Autowired
	private LsOrderrecordMapper lsOrderrecordMapper;
	@Autowired
	private LsOrderbuyerMapper lsOrderbuyerMapper;
	@Autowired
	private LsOrdersellerMapper lsOrdersellerMapper;
	
	@Override
	@Transactional
	public void saveMain(LsOrderrecord lsOrderrecord, List<LsOrderbuyer> lsOrderbuyerList,List<LsOrderseller> lsOrdersellerList) {
		lsOrderrecordMapper.insert(lsOrderrecord);
		if(lsOrderbuyerList!=null && lsOrderbuyerList.size()>0) {
			for(LsOrderbuyer entity:lsOrderbuyerList) {
				//外键设置
				entity.setOrderId(lsOrderrecord.getId());
				lsOrderbuyerMapper.insert(entity);
			}
		}
		if(lsOrdersellerList!=null && lsOrdersellerList.size()>0) {
			for(LsOrderseller entity:lsOrdersellerList) {
				//外键设置
				entity.setOrderId(lsOrderrecord.getId());
				lsOrdersellerMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(LsOrderrecord lsOrderrecord,List<LsOrderbuyer> lsOrderbuyerList,List<LsOrderseller> lsOrdersellerList) {
		lsOrderrecordMapper.updateById(lsOrderrecord);
		
		//1.先删除子表数据
		lsOrderbuyerMapper.deleteByMainId(lsOrderrecord.getId());
		lsOrdersellerMapper.deleteByMainId(lsOrderrecord.getId());
		
		//2.子表数据重新插入
		if(lsOrderbuyerList!=null && lsOrderbuyerList.size()>0) {
			for(LsOrderbuyer entity:lsOrderbuyerList) {
				//外键设置
				entity.setOrderId(lsOrderrecord.getId());
				lsOrderbuyerMapper.insert(entity);
			}
		}
		if(lsOrdersellerList!=null && lsOrdersellerList.size()>0) {
			for(LsOrderseller entity:lsOrdersellerList) {
				//外键设置
				entity.setOrderId(lsOrderrecord.getId());
				lsOrdersellerMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		lsOrderbuyerMapper.deleteByMainId(id);
		lsOrdersellerMapper.deleteByMainId(id);
		lsOrderrecordMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			lsOrderbuyerMapper.deleteByMainId(id.toString());
			lsOrdersellerMapper.deleteByMainId(id.toString());
			lsOrderrecordMapper.deleteById(id);
		}
	}
	
}
