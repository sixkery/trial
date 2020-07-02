package com.sixkery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author sixkery
 * @date 2020/6/15
 */
@Data
public class BlogModel implements Serializable {

    private Long id;

    private String title;

    private String description;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime created;

    private Integer status;
}
