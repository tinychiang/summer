package com.summer.cache.es.repository;

import com.summer.cache.es.document.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>贴文CURD</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
public interface PostRepository extends ElasticsearchRepository<Post, Long> {
}