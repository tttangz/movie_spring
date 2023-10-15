package com.movie.mapper;

import com.movie.domain.MovieDetailInfo;
import com.ruoyi.common.datasource.annotation.Master;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Master
public interface MovieDetailInfoMapper {
    public MovieDetailInfo selectMovieDetailInfoById(Integer id);
}