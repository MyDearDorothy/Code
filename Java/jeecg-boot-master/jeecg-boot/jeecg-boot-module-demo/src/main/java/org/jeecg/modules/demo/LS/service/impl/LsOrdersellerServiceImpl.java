package org.jeecg.modules.demo.LS.service.impl;

import org.jeecg.modules.demo.LS.entity.LsOrderseller;
import org.jeecg.modules.demo.LS.mapper.LsOrdersellerMapper;
import org.jeecg.modules.demo.LS.service.ILsOrdersellerService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单商家表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Service
public class LsOrdersellerServiceImpl extends ServiceImpl<LsOrdersellerMapper, LsOrderseller> implements ILsOrdersellerService {
	
	@Autowired
	private LsOrdersellerMapper lsOrdersellerMapper;
	
	@Override
	public List<LsOrderseller> selectByMainId(String mainId) {
		return lsOrdersellerMapper.selectByMainId(mainId);
	}
}
