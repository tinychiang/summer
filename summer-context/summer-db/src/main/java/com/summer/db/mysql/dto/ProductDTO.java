package com.summer.db.mysql.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 产品信息 DTO
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-04
 */
@ToString
public class ProductDTO {

    @Data
    @ApiModel(value = "ProductQuery", description = "产品查询")
    public static class ProductQuery implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "Id")
        private Integer id;

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "类型")
        private Integer type;

        @ApiModelProperty(value = "详细")
        private String detail;

        @ApiModelProperty(value = "更新人")
        private Integer updateUser;

        @ApiModelProperty(value = "更新时间")
        private LocalDateTime updateTime;

        @ApiModelProperty(value = "创建人")
        private Integer createUser;

        @ApiModelProperty(value = "创建时间")
        private LocalDateTime createTime;
    }

}