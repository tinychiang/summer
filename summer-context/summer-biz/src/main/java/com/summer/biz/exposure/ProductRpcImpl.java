package com.summer.biz.exposure;

import com.summer.api.ProductRpc;
import com.summer.api.dto.ProductQueryDTO;
import com.summer.api.vo.ProductQueryVO;
import com.summer.db.mysql.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * RPC接口实现类
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-09-13
 */
public class ProductRpcImpl implements ProductRpc {

    private final ProductMapper productMapper;

    @Override
    public List<ProductQueryVO> query(ProductQueryDTO productQueryDTO) {
        return null;
    }

    @Autowired
    public ProductRpcImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
}
