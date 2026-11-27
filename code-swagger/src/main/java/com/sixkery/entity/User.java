package com.sixkery.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 用户实体
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User Entity")
public class User implements Serializable {
    private static final long serialVersionUID = 5057954049311281252L;
    /**
     * 主键id
     */
    @Schema(description = "主键id")
    private Integer id;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String name;
    /**
     * 工作岗位
     */
    @Schema(description = "工作岗位")
    private String job;
}
