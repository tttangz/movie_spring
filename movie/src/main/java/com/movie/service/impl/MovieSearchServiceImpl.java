package com.movie.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.movie.mapperInterface.Id4urlMapper;
import com.movie.pojo.Id4url;
import com.movie.pojo.MovieDetailInfo;
import com.movie.service.MovieSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movie.mapperInterface.MovieSimpleInfoMapper;
import com.movie.mapperInterface.MovieDetailInfoMapper;
import com.movie.pojo.MovieSimpleInfo;

/**
 * 搜索Service业务层处理
 *
 * @author ruoyi
 * @date 2023-08-20
 */
@Service
public class MovieSearchServiceImpl implements MovieSearchService
{
    @Autowired
    private MovieSimpleInfoMapper movieSimpleInfoMapper;

    @Autowired
    private MovieDetailInfoMapper movieDetailInfoMapper;

    @Autowired
    private Id4urlMapper id4urlMapper;

    /**
     * 查询搜索
     *
     * @param id 搜索主键
     * @return 搜索
     */
    @Override
    public MovieSimpleInfo selectMovieSimpleInfoById(Integer id)
    {
        return movieSimpleInfoMapper.selectMovieSimpleInfoById(id);
    }

    @Override
    public List<MovieSimpleInfo> selectMovieSimpleInfoByType(String type)
    {
        return movieSimpleInfoMapper.selectMovieSimpleInfoByType(type);
    }

    @Override
    public List<MovieSimpleInfo> selectMovieSimpleInfoByTypeAndTag(String type, String tag)
    {
        List<String> typeList = new ArrayList<>();
        typeList.add(type);
        String[] tagArr = tag.split("-");
        List<String> tagList = new ArrayList<>();
        for (String item: tagArr) {
            tagList.add(item);
        }
        HashMap<String,List<String>> map = new HashMap<>();
        map.put("type", typeList);
        map.put("tag", tagList);
        return movieSimpleInfoMapper.selectMovieSimpleInfoByTypeAndTag(map);
    }

    /**
     * 查询搜索列表
     *
     * @param movieSimpleInfo 搜索
     * @return 搜索
     */
    @Override
    public List<MovieSimpleInfo> selectMovieSimpleInfoList(MovieSimpleInfo movieSimpleInfo)
    {
        return movieSimpleInfoMapper.selectMovieSimpleInfoList(movieSimpleInfo);
    }

    /**
     * 新增搜索
     *
     * @param movieSimpleInfo 搜索
     * @return 结果
     */
    @Override
    public int insertMovieSimpleInfo(MovieSimpleInfo movieSimpleInfo)
    {
        return movieSimpleInfoMapper.insertMovieSimpleInfo(movieSimpleInfo);
    }

    /**
     * 修改搜索
     *
     * @param movieSimpleInfo 搜索
     * @return 结果
     */
    @Override
    public int updateMovieSimpleInfo(MovieSimpleInfo movieSimpleInfo)
    {
        return movieSimpleInfoMapper.updateMovieSimpleInfo(movieSimpleInfo);
    }

    /**
     * 批量删除搜索
     *
     * @param ids 需要删除的搜索主键
     * @return 结果
     */
    @Override
    public int deleteMovieSimpleInfoByIds(Integer[] ids)
    {
        return movieSimpleInfoMapper.deleteMovieSimpleInfoByIds(ids);
    }

    /**
     * 删除搜索信息
     *
     * @param id 搜索主键
     * @return 结果
     */
    @Override
    public int deleteMovieSimpleInfoById(Integer id)
    {
        return movieSimpleInfoMapper.deleteMovieSimpleInfoById(id);
    }

    @Override
    public MovieDetailInfo selectMovieDetailInfoById(Integer id) {
        return movieDetailInfoMapper.selectMovieDetailInfoById(id);
    }

    @Override
    public Id4url selectId4urlByIdAndEpisode(Integer id, Integer episode) {
        Map<String,Integer> item = new HashMap<>();
        item.put("id",id);
        item.put("episode",episode);
        return id4urlMapper.selectId4urlByIdAndEpisode(item);
    }
}
