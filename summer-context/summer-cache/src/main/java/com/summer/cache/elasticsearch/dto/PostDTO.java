package com.summer.cache.elasticsearch.dto;

import com.summer.frame.elasticsearch.AbstractPageHelper;
import com.summer.frame.elasticsearch.stereotype.annotation.Document;
import com.summer.frame.elasticsearch.stereotype.annotation.field.TermField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>贴文 - 查询DTO</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@ToString
public class PostDTO {

    /**
     * 聚合查询DTO
     */
    @ApiModel(value = "PostAggregation")
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Document(indices = {"post"})
    public static class FilterAggregation extends AbstractPageHelper {
        /**
         * Id
         */
        @ApiModelProperty(value = "@Id")
        @TermField
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
         * 发布日期起始日期
         */
        @ApiModelProperty(value = "发布日期起始日期")
        private String beginDate;
        /**
         * 发布日期终止日期
         */
        @ApiModelProperty(value = "发布日期终止日期")
        private String endDate;
        /**
         * 内容
         */
        @ApiModelProperty(value = "贴文内容")
        private String content;
    }
}