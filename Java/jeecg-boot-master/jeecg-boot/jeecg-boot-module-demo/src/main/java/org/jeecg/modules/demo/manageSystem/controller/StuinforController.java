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
import org.jeecg.modules.demo.manageSystem.entity.Stuinfor;
import org.jeecg.modules.demo.manageSystem.service.IStuinforService;

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
 * @Description: 学生信息
 * @Author: jeecg-boot
 * @Date:   2022-03-26
 * @Version: V1.0
 */
@Api(tags="学生信息")
@RestController
@RequestMapping("/manageSystem/stuinfor")
@Slf4j
public class StuinforController extends JeecgController<Stuinfor, IStuinforService> {
	@Autowired
	private IStuinforService stuinforService;
	
	/**
	 * 分页列表查询
	 *
	 * @param stuinfor
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "学生信息-分页列表查询")
	@ApiOperation(value="学生信息-分页列表查询", notes="学生信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Stuinfor>> queryPageList(Stuinfor stuinfor,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Stuinfor> queryWrapper = QueryGenerator.initQueryWrapper(stuinfor, req.getParameterMap());
		Page<Stuinfor> page = new Page<Stuinfor>(pageNo, pageSize);
		IPage<Stuinfor> pageList = stuinforService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param stuinfor
	 * @return
	 */
	@AutoLog(value = "学生信息-添加")
	@ApiOperation(value="学生信息-添加", notes="学生信息-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Stuinfor stuinfor) {
		stuinforService.save(stuinfor);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param stuinfor
	 * @return
	 */
	@AutoLog(value = "学生信息-编辑")
	@ApiOperation(value="学生信息-编辑", notes="学生信息-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Stuinfor stuinfor) {
		stuinforService.updateById(stuinfor);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "学生信息-通过id删除")
	@ApiOperation(value="学生信息-通过id删除", notes="学生信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		stuinforService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "学生信息-批量删除")
	@ApiOperation(value="学生信息-批量删除", notes="学生信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.stuinforService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "学生信息-通过id查询")
	@ApiOperation(value="学生信息-通过id查询", notes="学生信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Stuinfor> queryById(@RequestParam(name="id",required=true) String id) {
		Stuinfor stuinfor = stuinforService.getById(id);
		if(stuinfor==null) {
			return Result.error("未找到对应数据",null);
		}
		return Result.OK(stuinfor);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param stuinfor
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Stuinfor stuinfor) {
        return super.exportXls(request, stuinfor, Stuinfor.class, "学生信息");
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
        return super.importExcel(request, response, Stuinfor.class);
    }

}
