package com.summer.biz.service.impl;

import com.summer.biz.service.PostService;
import com.summer.cache.elasticsearch.document.Post;
import com.summer.cache.elasticsearch.dto.PostDTO;
import com.summer.cache.elasticsearch.repository.PostRepository;
import com.summer.cache.elasticsearch.vo.PostVO;
import com.summer.frame.elasticsearch.ElasticsearchAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>贴文 - 业务处理</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@Service
public class PostServiceImpl implements PostService {

    private final ElasticsearchAdapter elasticsearchAdapter;

    private final PostRepository postRepository;

    @Override
    public Post findOne(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.orElse(new Post());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        postOptional.ifPresent(postRepository::delete);
    }

    @Override
    public List<PostVO> complicatedQuery(PostDTO.FilterAggregation filterAggregation) {
        SearchHits<PostVO> searchHits = elasticsearchAdapter.search(filterAggregation, PostVO.class);
        if (searchHits.hasSearchHits()) {
            return searchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
        }
        return null;
    }

    @Autowired
    public PostServiceImpl(ElasticsearchAdapter elasticsearchAdapter, PostRepository postRepository) {
        this.elasticsearchAdapter = elasticsearchAdapter;
        this.postRepository = postRepository;
    }
}