package com.spring.boot.dm.dao.mapper;

import com.spring.boot.dm.dao.entity.ProductReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rock.jiang
 * @since 2021-03-24
 */
@Repository
public interface ProductReviewMapper extends BaseMapper<ProductReview> {
    ProductReview selectByProductId(String productId);
}
