package com.summer.biz.service;

import com.summer.cache.es.document.Post;

import java.util.List;

/**
 * <p>贴文 - 业务接口</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
public interface PostService {

    /**
     * Id查询单条记录
     *
     * @param id Id
     * @return 记录
     * @author Tiny Chiang
     * @since 1.0.0
     */
    Post findOne(Long id);

    /**
     * 新增或修改记录
     *
     * @param post 记录
     * @author Tiny Chiang
     * @since 1.0.0
     */
    void save(Post post);

    /**
     * 删除记录
     *
     * @param id Id
     * @author Tiny Chiang
     * @since 1.0.0
     */
    void delete(Long id);

}