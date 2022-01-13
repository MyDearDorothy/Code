package org.jeecg.modules.demo.LS.service;

import org.jeecg.modules.demo.LS.entity.LsOrderbuyer;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单买家表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
public interface ILsOrderbuyerService extends IService<LsOrderbuyer> {

	public List<LsOrderbuyer> selectByMainId(String mainId);
}
