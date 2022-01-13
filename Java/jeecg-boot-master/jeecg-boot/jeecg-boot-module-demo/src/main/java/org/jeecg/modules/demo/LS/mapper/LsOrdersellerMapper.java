package org.jeecg.modules.demo.LS.mapper;

import java.util.List;
import org.jeecg.modules.demo.LS.entity.LsOrderseller;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单商家表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
public interface LsOrdersellerMapper extends BaseMapper<LsOrderseller> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<LsOrderseller> selectByMainId(@Param("mainId") String mainId);
}
