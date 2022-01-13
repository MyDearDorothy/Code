package org.jeecg.modules.demo.LS.service;

import org.jeecg.modules.demo.LS.entity.LsOrderseller;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 订单商家表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
public interface ILsOrdersellerService extends IService<LsOrderseller> {

	public List<LsOrderseller> selectByMainId(String mainId);
}
