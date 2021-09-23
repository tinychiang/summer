package com.summer.biz.exposure;

import com.summer.api.ProductRpc;
import com.summer.api.dto.ProductQueryDTO;
import com.summer.api.vo.ProductQueryVO;
import com.summer.db.mysql.mapper.ProductMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * RPC接口实现类
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-09-13
 */
public class ProductRpcImpl implements ProductRpc {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<ProductQueryVO> query(ProductQueryDTO productQueryDTO) {
        return null;
    }

}
