package com.summer.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * RPC 入参
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-12
 */
@Data
@ApiModel(value = "ProductRpcDTO", description = "RPC 入参")
public class ProductQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

}
