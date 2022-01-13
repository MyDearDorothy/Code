package org.jeecg.modules.demo.LS.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.LS.entity.LsSeller;
import org.jeecg.modules.demo.LS.service.ILsSellerService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 商家信息
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Api(tags="商家信息")
@RestController
@RequestMapping("/LS/lsSeller")
@Slf4j
public class LsSellerController extends JeecgController<LsSeller, ILsSellerService> {
	@Autowired
	private ILsSellerService lsSellerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param lsSeller
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商家信息-分页列表查询")
	@ApiOperation(value="商家信息-分页列表查询", notes="商家信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LsSeller lsSeller,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LsSeller> queryWrapper = QueryGenerator.initQueryWrapper(lsSeller, req.getParameterMap());
		Page<LsSeller> page = new Page<LsSeller>(pageNo, pageSize);
		IPage<LsSeller> pageList = lsSellerService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param lsSeller
	 * @return
	 */
	@AutoLog(value = "商家信息-添加")
	@ApiOperation(value="商家信息-添加", notes="商家信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LsSeller lsSeller) {
		lsSellerService.save(lsSeller);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param lsSeller
	 * @return
	 */
	@AutoLog(value = "商家信息-编辑")
	@ApiOperation(value="商家信息-编辑", notes="商家信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LsSeller lsSeller) {
		lsSellerService.updateById(lsSeller);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商家信息-通过id删除")
	@ApiOperation(value="商家信息-通过id删除", notes="商家信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lsSellerService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商家信息-批量删除")
	@ApiOperation(value="商家信息-批量删除", notes="商家信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lsSellerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商家信息-通过id查询")
	@ApiOperation(value="商家信息-通过id查询", notes="商家信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LsSeller lsSeller = lsSellerService.getById(id);
		if(lsSeller==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(lsSeller);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param lsSeller
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, LsSeller lsSeller) {
        return super.exportXls(request, lsSeller, LsSeller.class, "商家信息");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, LsSeller.class);
    }

}
