package com.summer.db.elasticsearch.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>贴文 - View Object</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-29
 */
@Data
@ApiModel(value = "PostVO", description = "贴文信息")
public class PostVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    @ApiModelProperty(value = "Id")
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "贴文名称")
    private String name;
    /**
     * 类型
     */
    @ApiModelProperty(value = "贴文类型")
    private String type;
    /**
     * 作者
     */
    @ApiModelProperty(value = "贴文作者")
    private String author;
    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    private String releaseDate;
    /**
     * 内容, 文本类型, standard分词
     */
    @ApiModelProperty(value = "贴文内容")
    private String content;
}