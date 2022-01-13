package org.jeecg.modules.demo.LS.mapper;

import java.util.List;
import org.jeecg.modules.demo.LS.entity.LsOrderbuyer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 订单买家表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
public interface LsOrderbuyerMapper extends BaseMapper<LsOrderbuyer> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<LsOrderbuyer> selectByMainId(@Param("mainId") String mainId);
}
