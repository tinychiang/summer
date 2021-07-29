package com.summer.frame.elasticsearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.KeyValue;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * <p>ES - 分页助手</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@ApiModel(value = "AbstractPageHelper")
public abstract class AbstractPageHelper implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ES默认查询最大深度10000条
     */
    private static final int ES_QUERY_MAX_DEPTH = 10000;
    /**
     * 页码, 0开始
     */
    @ApiModelProperty(value = "页码")
    private Integer from;
    /**
     * 数量, 注: 遵循ES查询数量限定
     */
    @ApiModelProperty(value = "数量")
    private Integer size;
    /**
     * 超过最大查询深度, 使用游标查询
     */
    @ApiModelProperty(value = "游标")
    private String scrollId;
    /**
     * 条件排序集合; KeyValue - K: 字段名称, V: 排序规则
     */
    @ApiModelProperty(value = "排序")
    private List<KeyValue<String, SortOrder>> sorters;

    public Integer getFrom() {
        from = from == null || from < 0 ? 0 : from;
        int actualFrom = this.getSize() * from;
        Assert.isTrue(actualFrom + size > ES_QUERY_MAX_DEPTH,
                "From add size is over max depth. Please use scroll to query.");
        return actualFrom;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        size = size == null ? ES_QUERY_MAX_DEPTH : size;
        Assert.isTrue(size > ES_QUERY_MAX_DEPTH,
                "Elastic search query size is over max depth. Please use scroll to query.");
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<KeyValue<String, SortOrder>> getSorters() {
        return sorters;
    }

    public void setSorters(List<KeyValue<String, SortOrder>> sorters) {
        this.sorters = sorters;
    }
}