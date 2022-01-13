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
import org.jeecg.modules.demo.LS.entity.LsBuyer;
import org.jeecg.modules.demo.LS.service.ILsBuyerService;

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
 * @Description: 买家信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Api(tags="买家信息表")
@RestController
@RequestMapping("/LS/lsBuyer")
@Slf4j
public class LsBuyerController extends JeecgController<LsBuyer, ILsBuyerService> {
	@Autowired
	private ILsBuyerService lsBuyerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param lsBuyer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "买家信息表-分页列表查询")
	@ApiOperation(value="买家信息表-分页列表查询", notes="买家信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LsBuyer lsBuyer,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LsBuyer> queryWrapper = QueryGenerator.initQueryWrapper(lsBuyer, req.getParameterMap());
		Page<LsBuyer> page = new Page<LsBuyer>(pageNo, pageSize);
		IPage<LsBuyer> pageList = lsBuyerService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param lsBuyer
	 * @return
	 */
	@AutoLog(value = "买家信息表-添加")
	@ApiOperation(value="买家信息表-添加", notes="买家信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LsBuyer lsBuyer) {
		lsBuyerService.save(lsBuyer);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param lsBuyer
	 * @return
	 */
	@AutoLog(value = "买家信息表-编辑")
	@ApiOperation(value="买家信息表-编辑", notes="买家信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LsBuyer lsBuyer) {
		lsBuyerService.updateById(lsBuyer);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "买家信息表-通过id删除")
	@ApiOperation(value="买家信息表-通过id删除", notes="买家信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lsBuyerService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "买家信息表-批量删除")
	@ApiOperation(value="买家信息表-批量删除", notes="买家信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lsBuyerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "买家信息表-通过id查询")
	@ApiOperation(value="买家信息表-通过id查询", notes="买家信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LsBuyer lsBuyer = lsBuyerService.getById(id);
		if(lsBuyer==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(lsBuyer);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param lsBuyer
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, LsBuyer lsBuyer) {
        return super.exportXls(request, lsBuyer, LsBuyer.class, "买家信息表");
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
        return super.importExcel(request, response, LsBuyer.class);
    }

}
