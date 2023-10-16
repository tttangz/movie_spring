package com.movie.mapperInterface;

import com.movie.pojo.MovieDetailInfo;
import com.ruoyi.common.datasource.annotation.Master;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Master
public interface MovieDetailInfoMapper {
    public MovieDetailInfo selectMovieDetailInfoById(Integer id);
}