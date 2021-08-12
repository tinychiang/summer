package com.summer.db.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summer.db.mysql.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品信息 Mapper
 *
 * @author Tiny Chiang
 * @version 1.0.0
 * @date 2021-08-04
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}