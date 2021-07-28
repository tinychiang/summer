package com.summer.cache.es.stereotype;

import com.summer.cache.es.AbstractPageHelper;
import com.summer.cache.es.stereotype.annotation.field.StringField;
import org.springframework.stereotype.Component;

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
public class ElasticsearchContext implements ComplicatedRetrieval {

    @StringField()
    private String abc;

    @Override
    public <S extends AbstractPageHelper> List<Map<String, Object>> search(S condition) {
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
}