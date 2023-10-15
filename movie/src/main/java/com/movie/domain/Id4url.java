package com.movie.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * id4url
 * @author 
 */
@Data
public class Id4url implements Serializable {
    private Integer id;

    private Integer movieId;

    private Short nowEpisode;

    private String url;

    private String siteFrom;

    private String iframe;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}