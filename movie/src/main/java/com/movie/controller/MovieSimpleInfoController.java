package com.movie.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.movie.service.IMovieSimpleInfoService;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.movie.domain.MovieSimpleInfo;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 搜索Controller
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
@RestController
@RequestMapping("/movie")
public class MovieSimpleInfoController extends BaseController
{
    @Autowired
    private IMovieSimpleInfoService movieSimpleInfoService;

    /**
     * 查询搜索列表
     */
    //@RequiresPermissions("movie:movie:list")
    @GetMapping("/list")
    public R list(MovieSimpleInfo movieSimpleInfo)
    {
        startPage();
        List<MovieSimpleInfo> list = movieSimpleInfoService.selectMovieSimpleInfoList(movieSimpleInfo);
        TableDataInfo dataTable = getDataTable(list);
        return R.ok(dataTable);
    }

    /**
     * 查询搜索列表
     */
    @GetMapping("/first")
    public R list()
    {
        startPage();
        List<MovieSimpleInfo> list = movieSimpleInfoService.defaultMovieSimpleInfoList();
        TableDataInfo dataTable = getDataTable(list);
        return R.ok(dataTable);
    }

    /**
     * 导出搜索列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, MovieSimpleInfo movieSimpleInfo)
    {
        List<MovieSimpleInfo> list = movieSimpleInfoService.selectMovieSimpleInfoList(movieSimpleInfo);
        ExcelUtil<MovieSimpleInfo> util = new ExcelUtil<MovieSimpleInfo>(MovieSimpleInfo.class);
        util.exportExcel(response, list, "搜索数据");
    }

    /**
     * 获取搜索详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Integer id)
    {
        return R.ok(movieSimpleInfoService.selectMovieSimpleInfoById(id));
        //return success(movieSimpleInfoService.selectMovieSimpleInfoById(id));
    }

//    /**
//     * 新增搜索
//     */
//    @RequiresPermissions("movie:movie:add")
//    @Log(title = "搜索", businessType = BusinessType.INSERT)
//    @PostMapping
//    public R add(@RequestBody MovieSimpleInfo movieSimpleInfo)
//    {
//        return toAjax(movieSimpleInfoService.insertMovieSimpleInfo(movieSimpleInfo));
//    }
//
//    /**
//     * 修改搜索
//     */
//    @RequiresPermissions("movie:movie:edit")
//    @Log(title = "搜索", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public R edit(@RequestBody MovieSimpleInfo movieSimpleInfo)
//    {
//        return toAjax(movieSimpleInfoService.updateMovieSimpleInfo(movieSimpleInfo));
//    }
//
//    /**
//     * 删除搜索
//     */
//    @RequiresPermissions("movie:movie:remove")
//    @Log(title = "搜索", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public R remove(@PathVariable Integer[] ids)
//    {
//        return toAjax(movieSimpleInfoService.deleteMovieSimpleInfoByIds(ids));
//    }
}
