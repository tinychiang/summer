package com.summer.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * RPC 出参
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-12
 */
@Data
@ApiModel(value = "ProductRpcVO", description = "RPC 出参")
public class ProductQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "详细")
    private String detail;

}
