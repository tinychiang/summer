package com.summer.api;

import com.summer.api.dto.ProductQueryDTO;
import com.summer.api.vo.ProductQueryVO;
import com.summer.frame.commons.annotation.Rpc;

import java.util.List;

/**
 * RPC 接口
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-12
 */
@Rpc(name = "summer-netflix")
public interface ProductRpc {

    /**
     * 查询
     *
     * @param productQueryDTO 条件
     * @return 结果集
     * @since 1.0.0
     */
    List<ProductQueryVO> query(ProductQueryDTO productQueryDTO);

}