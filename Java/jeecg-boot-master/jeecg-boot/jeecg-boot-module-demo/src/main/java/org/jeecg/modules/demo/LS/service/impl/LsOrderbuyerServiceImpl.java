package org.jeecg.modules.demo.LS.service.impl;

import org.jeecg.modules.demo.LS.entity.LsOrderbuyer;
import org.jeecg.modules.demo.LS.mapper.LsOrderbuyerMapper;
import org.jeecg.modules.demo.LS.service.ILsOrderbuyerService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单买家表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Service
public class LsOrderbuyerServiceImpl extends ServiceImpl<LsOrderbuyerMapper, LsOrderbuyer> implements ILsOrderbuyerService {
	
	@Autowired
	private LsOrderbuyerMapper lsOrderbuyerMapper;
	
	@Override
	public List<LsOrderbuyer> selectByMainId(String mainId) {
		return lsOrderbuyerMapper.selectByMainId(mainId);
	}
}
