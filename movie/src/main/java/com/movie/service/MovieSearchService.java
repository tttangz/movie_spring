package com.movie.service;

import java.util.List;

import com.movie.pojo.Id4url;
import com.movie.pojo.MovieDetailInfo;
import com.movie.pojo.MovieSimpleInfo;

/**
 * movie接口
 *
 * @author tangzhao
 * @date 2023-10-16
 */
public interface MovieSearchService
{
    /**
     * 查询搜索
     *
     * @param id 搜索主键
     * @return 搜索
     */
    public MovieSimpleInfo selectMovieSimpleInfoById(Integer id);

    public List<MovieSimpleInfo> selectMovieSimpleInfoByTypeAndTag(String type, String tag);

    public List<MovieSimpleInfo> selectMovieSimpleInfoByType(String type);

    /**
     * 查询搜索列表
     *
     * @param movieSimpleInfo 搜索
     * @return 搜索集合
     */
    public List<MovieSimpleInfo> selectMovieSimpleInfoList(MovieSimpleInfo movieSimpleInfo);

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


    public MovieDetailInfo selectMovieDetailInfoById(Integer id);

    public Id4url selectId4urlByIdAndEpisode(Integer id, Integer episode);
}
