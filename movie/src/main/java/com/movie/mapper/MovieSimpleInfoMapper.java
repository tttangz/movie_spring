package com.movie.mapper;

import java.util.List;
import java.util.Map;

import com.movie.domain.MovieSimpleInfo;
import com.ruoyi.common.datasource.annotation.Master;
import org.apache.ibatis.annotations.Mapper;

/**
 * 搜索Mapper接口
 * 
 * @author ruoyi
 * @date 2023-08-20
 */
@Mapper
@Master
public interface MovieSimpleInfoMapper
{
    /**
     * 查询搜索
     *
     * @return 搜索
     */
    List<MovieSimpleInfo> defaultMovieSimpleInfoList();
    /**
     * 查询搜索
     * 
     * @param id 搜索主键
     * @return 搜索
     */
    public MovieSimpleInfo selectMovieSimpleInfoById(Integer id);

    /**
     * 查询搜索
     *
     * @param map 搜索类型
     * @return 搜索
     */
    public List<MovieSimpleInfo> selectMovieSimpleInfoByType(String type);

    public List<MovieSimpleInfo> selectMovieSimpleInfoByTypeAndTag(Map map);

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
     * 删除搜索
     * 
     * @param id 搜索主键
     * @return 结果
     */
    public int deleteMovieSimpleInfoById(Integer id);

    /**
     * 批量删除搜索
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMovieSimpleInfoByIds(Integer[] ids);


}
