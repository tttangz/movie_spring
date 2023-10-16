package com.movie.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * update_cache
 * @author
 */
@Data
public class UpdateCache implements Serializable {
    private Integer id;

    private String name;

    private String siteFrom;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}