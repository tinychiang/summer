package com.summer.biz.controller;

import com.summer.biz.service.PostService;
import com.summer.cache.elasticsearch.document.Post;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>贴文 - 控制器</p>
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-07-23
 */
@Api(value = "贴文API")
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @ApiOperation(value = "查询")
    @GetMapping("/find/{id}")
    public Post findOne(@PathVariable Long id) {
        return postService.findOne(id);
    }

    @ApiOperation(value = "新增或修改")
    @PostMapping("/save")
    public void save(@RequestBody Post post) {
        postService.save(post);
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
}