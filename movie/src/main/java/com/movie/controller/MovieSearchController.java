package com.movie.controller;

import com.movie.service.MovieSearchService;
import com.movie.test.MyValid;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.web.controller.BaseController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 搜索Controller
 *
 * @author ruoyi
 * @date 2023-08-20
 */
@Validated
@RestController
@RequestMapping("/movie")
public class MovieSearchController extends BaseController
{
    @Autowired
    private MovieSearchService movieSearchService;

    /**
     * 根据id获取详细数据
     */
    //@MyValid (value = 0, message = "id最小为1")
    @GetMapping(value = "/detailInfo/id/{id}")
    public R getDetailInfoById(@MyValid(value = 1, message = "id最小为1") @NotNull(message = "id不能为空！") @PathVariable("id") Integer id)
    {
        return R.ok(movieSearchService.selectMovieDetailInfoById(id));
    }

    /**
     * 根据id获取简要数据
     */
    @GetMapping(value = "/simpleInfo/id/{id}")
    public R getSimpleInfoById(@NotNull(message = "id不能为空！") @Min(value = 1,message = "id最小为1") @PathVariable("id") Integer id)
    {
        return R.ok(movieSearchService.selectMovieSimpleInfoById(id));
    }

    /**
     * 根据type获取数据
     */
    @GetMapping(value = "/type/{type}")
    public R getMovieListByType(@NotNull(message = "type不能为空！") @PathVariable("type") String type)
    {
        return R.ok(movieSearchService.selectMovieSimpleInfoByType(type));
    }

    /**
     * 根据type 和 tag获取数据
     */
    @GetMapping(value = "/typeAndTag/{type}/{tag}")
    public R getMovieListByTypeAndTag(@NotNull(message = "type不能为空！") @PathVariable("type") String type, @PathVariable ("tag") String tag)
    {
        return R.ok(movieSearchService.selectMovieSimpleInfoByTypeAndTag(type, tag));
    }

    /**
     * 根据type 和 tag获取数据
     */
    @GetMapping(value = "/show/id/{id}")
    public R getMovieId4UrlData(@NotNull(message = "type不能为空！") @PathVariable("id") Integer id, @NotNull(message = "episode不能为空！") @RequestParam ("episode") Integer episode)
    {
        return R.ok(movieSearchService.selectId4urlByIdAndEpisode(id, episode));
    }

//    /**
//     * 查询搜索列表
//     */
//    //@RequiresPermissions("movie:movie:list")
//    @GetMapping("/list")
//    public R list(MovieSimpleInfo movieSimpleInfo)
//    {
//        startPage();
//        List<MovieSimpleInfo> list = movieSearchService.selectMovieSimpleInfoList(movieSimpleInfo);
//        TableDataInfo dataTable = getDataTable(list);
//        return R.ok(dataTable);
//    }

//    /**
//     * 导出搜索列表到excel
//     */
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, MovieSimpleInfo movieSimpleInfo)
//    {
//        List<MovieSimpleInfo> list = movieSearchService.selectMovieSimpleInfoList(movieSimpleInfo);
//        ExcelUtil<MovieSimpleInfo> util = new ExcelUtil<MovieSimpleInfo>(MovieSimpleInfo.class);
//        util.exportExcel(response, list, "搜索数据");
//    }
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
