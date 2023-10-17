package com.movie.mapperInterface;

import com.movie.pojo.Id4url;
import com.ruoyi.common.datasource.annotation.Master;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
@Master
public interface Id4urlMapper {
    public Id4url selectId4urlByIdAndEpisode(Map map);
}