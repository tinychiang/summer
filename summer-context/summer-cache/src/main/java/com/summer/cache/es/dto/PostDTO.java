package com.summer.cache.es.dto;

import com.summer.cache.es.AbstractPageHelper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
    public static class PostAggregation extends AbstractPageHelper {
        /**
         * Id
         */
        @ApiModelProperty(value = "@Id")
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