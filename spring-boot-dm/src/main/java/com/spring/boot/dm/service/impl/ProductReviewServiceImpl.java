package com.spring.boot.dm.service.impl;

import com.spring.boot.dm.dao.entity.ProductReview;
import com.spring.boot.dm.dao.mapper.ProductReviewMapper;
import com.spring.boot.dm.service.ProductReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rock.jiang
 * @since 2021-03-24
 */
@Service
public class ProductReviewServiceImpl extends ServiceImpl<ProductReviewMapper, ProductReview> implements ProductReviewService {

}
