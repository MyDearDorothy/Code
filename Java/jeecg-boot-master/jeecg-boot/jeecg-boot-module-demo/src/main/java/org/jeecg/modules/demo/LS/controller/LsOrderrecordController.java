package org.jeecg.modules.demo.LS.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.LS.entity.LsOrderbuyer;
import org.jeecg.modules.demo.LS.entity.LsOrderseller;
import org.jeecg.modules.demo.LS.entity.LsOrderrecord;
import org.jeecg.modules.demo.LS.vo.LsOrderrecordPage;
import org.jeecg.modules.demo.LS.service.ILsOrderrecordService;
import org.jeecg.modules.demo.LS.service.ILsOrderbuyerService;
import org.jeecg.modules.demo.LS.service.ILsOrdersellerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 订单信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Api(tags="订单信息表")
@RestController
@RequestMapping("/LS/lsOrderrecord")
@Slf4j
public class LsOrderrecordController {
	@Autowired
	private ILsOrderrecordService lsOrderrecordService;
	@Autowired
	private ILsOrderbuyerService lsOrderbuyerService;
	@Autowired
	private ILsOrdersellerService lsOrdersellerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param lsOrderrecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "订单信息表-分页列表查询")
	@ApiOperation(value="订单信息表-分页列表查询", notes="订单信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(LsOrderrecord lsOrderrecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<LsOrderrecord> queryWrapper = QueryGenerator.initQueryWrapper(lsOrderrecord, req.getParameterMap());
		Page<LsOrderrecord> page = new Page<LsOrderrecord>(pageNo, pageSize);
		IPage<LsOrderrecord> pageList = lsOrderrecordService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param lsOrderrecordPage
	 * @return
	 */
	@AutoLog(value = "订单信息表-添加")
	@ApiOperation(value="订单信息表-添加", notes="订单信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody LsOrderrecordPage lsOrderrecordPage) {
		LsOrderrecord lsOrderrecord = new LsOrderrecord();
		BeanUtils.copyProperties(lsOrderrecordPage, lsOrderrecord);
		lsOrderrecordService.saveMain(lsOrderrecord, lsOrderrecordPage.getLsOrderbuyerList(),lsOrderrecordPage.getLsOrdersellerList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param lsOrderrecordPage
	 * @return
	 */
	@AutoLog(value = "订单信息表-编辑")
	@ApiOperation(value="订单信息表-编辑", notes="订单信息表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody LsOrderrecordPage lsOrderrecordPage) {
		LsOrderrecord lsOrderrecord = new LsOrderrecord();
		BeanUtils.copyProperties(lsOrderrecordPage, lsOrderrecord);
		LsOrderrecord lsOrderrecordEntity = lsOrderrecordService.getById(lsOrderrecord.getId());
		if(lsOrderrecordEntity==null) {
			return Result.error("未找到对应数据");
		}
		lsOrderrecordService.updateMain(lsOrderrecord, lsOrderrecordPage.getLsOrderbuyerList(),lsOrderrecordPage.getLsOrdersellerList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单信息表-通过id删除")
	@ApiOperation(value="订单信息表-通过id删除", notes="订单信息表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		lsOrderrecordService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "订单信息表-批量删除")
	@ApiOperation(value="订单信息表-批量删除", notes="订单信息表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.lsOrderrecordService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单信息表-通过id查询")
	@ApiOperation(value="订单信息表-通过id查询", notes="订单信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		LsOrderrecord lsOrderrecord = lsOrderrecordService.getById(id);
		if(lsOrderrecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(lsOrderrecord);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单买家表通过主表ID查询")
	@ApiOperation(value="订单买家表主表ID查询", notes="订单买家表-通主表ID查询")
	@GetMapping(value = "/queryLsOrderbuyerByMainId")
	public Result<?> queryLsOrderbuyerListByMainId(@RequestParam(name="id",required=true) String id) {
		List<LsOrderbuyer> lsOrderbuyerList = lsOrderbuyerService.selectByMainId(id);
		return Result.OK(lsOrderbuyerList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单商家表通过主表ID查询")
	@ApiOperation(value="订单商家表主表ID查询", notes="订单商家表-通主表ID查询")
	@GetMapping(value = "/queryLsOrdersellerByMainId")
	public Result<?> queryLsOrdersellerListByMainId(@RequestParam(name="id",required=true) String id) {
		List<LsOrderseller> lsOrdersellerList = lsOrdersellerService.selectByMainId(id);
		return Result.OK(lsOrdersellerList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param lsOrderrecord
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, LsOrderrecord lsOrderrecord) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<LsOrderrecord> queryWrapper = QueryGenerator.initQueryWrapper(lsOrderrecord, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<LsOrderrecord> queryList = lsOrderrecordService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<LsOrderrecord> lsOrderrecordList = new ArrayList<LsOrderrecord>();
      if(oConvertUtils.isEmpty(selections)) {
          lsOrderrecordList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          lsOrderrecordList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<LsOrderrecordPage> pageList = new ArrayList<LsOrderrecordPage>();
      for (LsOrderrecord main : lsOrderrecordList) {
          LsOrderrecordPage vo = new LsOrderrecordPage();
          BeanUtils.copyProperties(main, vo);
          List<LsOrderbuyer> lsOrderbuyerList = lsOrderbuyerService.selectByMainId(main.getId());
          vo.setLsOrderbuyerList(lsOrderbuyerList);
          List<LsOrderseller> lsOrdersellerList = lsOrdersellerService.selectByMainId(main.getId());
          vo.setLsOrdersellerList(lsOrdersellerList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "订单信息表列表");
      mv.addObject(NormalExcelConstants.CLASS, LsOrderrecordPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单信息表数据", "导出人:"+sysUser.getRealname(), "订单信息表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<LsOrderrecordPage> list = ExcelImportUtil.importExcel(file.getInputStream(), LsOrderrecordPage.class, params);
              for (LsOrderrecordPage page : list) {
                  LsOrderrecord po = new LsOrderrecord();
                  BeanUtils.copyProperties(page, po);
                  lsOrderrecordService.saveMain(po, page.getLsOrderbuyerList(),page.getLsOrdersellerList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
