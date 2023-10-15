package com.movie.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.movie.service.MovieSearchService;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
public class MovieSearchController extends BaseController
{
    @Autowired
    private MovieSearchService movieSearchService;

    /**
     * 获取搜索详细信息
     */
    @GetMapping(value = "/detail/id/{id}")
    public R getDetailInfoById(@PathVariable("id") Integer id)
    {
        return R.ok(movieSearchService.selectMovieDetailInfoById(id));
        //return success(movieSimpleInfoService.selectMovieSimpleInfoById(id));
    }

    /**
     * 根据type 和 tag获取数据
     */
    @GetMapping(value = "/type/{type}")
    public R getSimpleInfoByType(@PathVariable("type") String type)
    //@RequestBody JSONObject param
    {
        if (type.equals("")) {
            return R.fail("type不能为空！");
        }
        return R.ok(movieSearchService.selectMovieSimpleInfoByType(type));
        //return success(movieSimpleInfoService.selectMovieSimpleInfoById(id));
    }

    /**
     * 根据type 和 tag获取数据
     */
    @GetMapping(value = "/typeAndTag/{type}/")
    public R getSimpleInfoByTypeAndTag(@PathVariable("type") String type, @RequestParam ("tag") String tag)
    //@RequestBody JSONObject param
    {
        if (type.equals("")) {
            return R.fail("type不能为空！");
        }
        return R.ok(movieSearchService.selectMovieSimpleInfoByTypeAndTag(type, tag));
        //return success(movieSimpleInfoService.selectMovieSimpleInfoById(id));
    }

    /**
     * 查询搜索列表
     */
    //@RequiresPermissions("movie:movie:list")
    @GetMapping("/list")
    public R list(MovieSimpleInfo movieSimpleInfo)
    {
        startPage();
        List<MovieSimpleInfo> list = movieSearchService.selectMovieSimpleInfoList(movieSimpleInfo);
        TableDataInfo dataTable = getDataTable(list);
        return R.ok(dataTable);
    }

    /**
     * 导出搜索列表到excel
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, MovieSimpleInfo movieSimpleInfo)
    {
        List<MovieSimpleInfo> list = movieSearchService.selectMovieSimpleInfoList(movieSimpleInfo);
        ExcelUtil<MovieSimpleInfo> util = new ExcelUtil<MovieSimpleInfo>(MovieSimpleInfo.class);
        util.exportExcel(response, list, "搜索数据");
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
