package org.jeecg.modules.demo.manageSystem.controller;

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
import org.jeecg.modules.demo.manageSystem.entity.Teainfor;
import org.jeecg.modules.demo.manageSystem.service.ITeainforService;

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
 * @Description: 教师信息
 * @Author: jeecg-boot
 * @Date:   2022-03-27
 * @Version: V1.0
 */
@Api(tags="教师信息")
@RestController
@RequestMapping("/manageSystem/teainfor")
@Slf4j
public class TeainforController extends JeecgController<Teainfor, ITeainforService> {
	@Autowired
	private ITeainforService teainforService;
	
	/**
	 * 分页列表查询
	 *
	 * @param teainfor
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "教师信息-分页列表查询")
	@ApiOperation(value="教师信息-分页列表查询", notes="教师信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Teainfor>> queryPageList(Teainfor teainfor,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Teainfor> queryWrapper = QueryGenerator.initQueryWrapper(teainfor, req.getParameterMap());
		Page<Teainfor> page = new Page<Teainfor>(pageNo, pageSize);
		IPage<Teainfor> pageList = teainforService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param teainfor
	 * @return
	 */
	@AutoLog(value = "教师信息-添加")
	@ApiOperation(value="教师信息-添加", notes="教师信息-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Teainfor teainfor) {
		teainforService.save(teainfor);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param teainfor
	 * @return
	 */
	@AutoLog(value = "教师信息-编辑")
	@ApiOperation(value="教师信息-编辑", notes="教师信息-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Teainfor teainfor) {
		teainforService.updateById(teainfor);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "教师信息-通过id删除")
	@ApiOperation(value="教师信息-通过id删除", notes="教师信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		teainforService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "教师信息-批量删除")
	@ApiOperation(value="教师信息-批量删除", notes="教师信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.teainforService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "教师信息-通过id查询")
	@ApiOperation(value="教师信息-通过id查询", notes="教师信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Teainfor> queryById(@RequestParam(name="id",required=true) String id) {
		Teainfor teainfor = teainforService.getById(id);
		if(teainfor==null) {
			return Result.error("未找到对应数据",null);
		}
		return Result.OK(teainfor);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param teainfor
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Teainfor teainfor) {
        return super.exportXls(request, teainfor, Teainfor.class, "教师信息");
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
        return super.importExcel(request, response, Teainfor.class);
    }

}
