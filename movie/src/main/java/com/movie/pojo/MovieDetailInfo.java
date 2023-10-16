package com.movie.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * movie_detail_info
 * @author 
 */
@Data
public class MovieDetailInfo implements Serializable {
    private Integer id;

    private String showtime;

    private String introduction;

    private String area;

    private String language;

    private String size;

    private Date createtime;

    private Date updatetime;

    private String director;

    private String palywright;

    private String timeLength;

    private static final long serialVersionUID = 1L;
}