package com.movie.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * movie_type
 * @author 
 */
@Data
public class MovieType implements Serializable {
    private Integer id;

    private String typeName;

    private String typeComment;

    private static final long serialVersionUID = 1L;
}