package com.summer.frame.elasticsearch.stereotype;

import com.summer.frame.elasticsearch.AbstractPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * Elasticsearch 核心处理
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-28
 */
@Component
public class ElasticsearchContext implements ElasticsearchMajor {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public <S extends AbstractPageHelper> List<Map<String, Object>> search(S condition) {
        Assert.notNull(condition, "Condition is none.");
        return null;
    }

    @Override
    public <S extends AbstractPageHelper, T> List<T> search(S condition, Class<T> clazz) {
        return null;
    }

    @Override
    public List<Map<String, Object>> searchByScroll(String scrollId) {
        return null;
    }

    @Override
    public <T> List<T> searchByScroll(String scrollId, Class<T> clazz) {
        return null;
    }

    /**
     * 判断属性是否为基本类型或者基本类型的封装类型
     *
     * @param object 对象
     * @return true: 符合, false: 不符合
     * @author Tiny Chiang
     * @since 1.0.0
     */
    private boolean isPrimitive(Object object) {
        if (object != null) {
            return object.getClass().isPrimitive() ||
                    object instanceof String ||
                    object instanceof Integer ||
                    object instanceof Long ||
                    object instanceof Double ||
                    object instanceof Boolean ||
                    object instanceof Float ||
                    object instanceof Byte ||
                    object instanceof Character ||
                    object instanceof Short;
        }
        return false;
    }

    @Autowired
    public ElasticsearchContext(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }
}