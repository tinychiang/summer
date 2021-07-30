package com.summer.frame.elasticsearch;

import com.summer.frame.elasticsearch.annotation.Document;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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
    public <T, E> SearchHits<E> search(@NonNull T condition, Class<E> target) {
        Class<?> clazz = condition.getClass();
        if (clazz.isAnnotationPresent(Document.class)) {
            Document document = clazz.getAnnotation(Document.class);
            return elasticsearchRestTemplate.search(this.queryBuilder(condition), target, IndexCoordinates.of(document.indices()));
        }
        // 未定义索引时全库检索
        return elasticsearchRestTemplate.search(this.queryBuilder(condition), target);
    }

    @Override
    public <T extends AbstractPageHelper, E> SearchHits<E> scrollSearch(@NonNull T condition, Class<E> target) {
        Class<?> clazz = condition.getClass();
        Assert.isTrue(clazz.isAnnotationPresent(Document.class), "Index is undefined.");
        String[] indices = clazz.getAnnotation(Document.class).indices();
        // 游标Id
        String scrollId = condition.getScrollId();
        // 游标生效时间
        long scrollTimeInMillis = condition.getScrollTimeInMillis();
        if (StringUtils.isEmpty(scrollId)) {
            elasticsearchRestTemplate.searchScrollStart(scrollTimeInMillis, this.queryBuilder(condition), target, IndexCoordinates.of(indices));
        }
        return elasticsearchRestTemplate.searchScrollContinue(scrollId, scrollTimeInMillis, target, IndexCoordinates.of(indices));
    }

    private <T> Query queryBuilder(T condition) {
        return Query.findAll();
    }

    /**
     * 判断对象为基本类型或基本封装类型
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