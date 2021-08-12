package com.summer.biz.service.impl;

import com.summer.api.ProductRpc;
import com.summer.api.dto.ProductQueryDTO;
import com.summer.api.vo.ProductQueryVO;
import com.summer.biz.service.ProductService;
import com.summer.db.mysql.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品 - 业务处理
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-12
 */
@Service
public class ProductServiceImpl implements ProductService, ProductRpc {

    private final ProductMapper productMapper;

    @Override
    public List<ProductQueryVO> query(ProductQueryDTO productQueryDTO) {
        return null;
    }

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

}