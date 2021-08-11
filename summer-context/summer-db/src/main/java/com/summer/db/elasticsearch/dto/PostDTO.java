package com.summer.db.elasticsearch.dto;

import com.summer.frame.elasticsearch.AbstractPageHelper;
import com.summer.frame.elasticsearch.annotation.Document;
import com.summer.frame.elasticsearch.annotation.field.RangeField;
import com.summer.frame.elasticsearch.annotation.field.StringField;
import com.summer.frame.elasticsearch.annotation.field.TermField;
import com.summer.frame.elasticsearch.enums.Range;
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
    @ApiModel(value = "PostAggregation", description = "聚合查询")
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Document(indices = {"post"})
    public static class FilterAggregation extends AbstractPageHelper {
        /**
         * Id
         */
        @ApiModelProperty(value = "@Id")
        private Long id;
        /**
         * 名称
         */
        @ApiModelProperty(value = "贴文名称")
        @TermField
        private String name;
        /**
         * 类型
         */
        @ApiModelProperty(value = "贴文类型")
        @TermField
        private String type;
        /**
         * 作者
         */
        @ApiModelProperty(value = "贴文作者")
        @TermField
        private String author;
        /**
         * 发布日期起始日期
         */
        @ApiModelProperty(value = "发布日期起始日期")
        @RangeField(field = "releaseDate", range = Range.GTE, format = "yyyy-MM-dd HH:mm:ss")
        private String beginDate;
        /**
         * 发布日期终止日期
         */
        @ApiModelProperty(value = "发布日期终止日期")
        @RangeField(field = "releaseDate", range = Range.LTE, format = "yyyy-MM-dd HH:mm:ss")
        private String endDate;
        /**
         * 内容
         */
        @ApiModelProperty(value = "贴文内容")
        @StringField
        private String content;
    }
}