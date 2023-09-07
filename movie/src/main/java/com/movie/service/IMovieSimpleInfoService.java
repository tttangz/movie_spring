package com.movie.service;

import java.util.List;
import com.movie.domain.MovieSimpleInfo;

/**
 * 搜索Service接口
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
public interface IMovieSimpleInfoService 
{
    /**
     * 查询搜索
     * 
     * @param id 搜索主键
     * @return 搜索
     */
    public MovieSimpleInfo selectMovieSimpleInfoById(Integer id);

    /**
     * 查询搜索列表
     * 
     * @param movieSimpleInfo 搜索
     * @return 搜索集合
     */
    public List<MovieSimpleInfo> selectMovieSimpleInfoList(MovieSimpleInfo movieSimpleInfo);

    /**
     * 查询搜索列表
     *
     * @return 搜索集合
     */
    public List<MovieSimpleInfo> defaultMovieSimpleInfoList();

    /**
     * 新增搜索
     * 
     * @param movieSimpleInfo 搜索
     * @return 结果
     */
    public int insertMovieSimpleInfo(MovieSimpleInfo movieSimpleInfo);

    /**
     * 修改搜索
     * 
     * @param movieSimpleInfo 搜索
     * @return 结果
     */
    public int updateMovieSimpleInfo(MovieSimpleInfo movieSimpleInfo);

    /**
     * 批量删除搜索
     * 
     * @param ids 需要删除的搜索主键集合
     * @return 结果
     */
    public int deleteMovieSimpleInfoByIds(Integer[] ids);

    /**
     * 删除搜索信息
     * 
     * @param id 搜索主键
     * @return 结果
     */
    public int deleteMovieSimpleInfoById(Integer id);
}
