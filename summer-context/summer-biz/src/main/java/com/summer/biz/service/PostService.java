package com.summer.biz.service;

import com.summer.db.elasticsearch.document.Post;
import com.summer.db.elasticsearch.dto.PostDTO;
import com.summer.db.elasticsearch.vo.PostVO;

import java.util.List;

/**
 * 贴文 - 业务接口
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

    /**
     * 条件、聚合查询
     *
     * @param filterAggregation 条件
     * @return 数据集
     * @author Tiny Chiang
     * @since 1.0.0
     */
    List<PostVO> complicatedQuery(PostDTO.FilterAggregation filterAggregation);

}