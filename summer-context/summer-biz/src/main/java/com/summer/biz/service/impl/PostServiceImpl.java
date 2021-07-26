package com.summer.biz.service.impl;

import com.summer.biz.service.PostService;
import com.summer.cache.es.document.Post;
import com.summer.cache.es.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>贴文 - 业务处理</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post findOne(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.orElse(null);
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

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}