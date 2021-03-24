package com.spring.boot.dm;

import com.spring.boot.dm.dao.entity.ProductReview;
import com.spring.boot.dm.dao.mapper.ProductReviewMapper;
import com.spring.boot.dm.service.ProductReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringBootDmApplicationTests {

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private ProductReviewMapper productReviewMapper;

    @Test
    void contextLoads() {
        ProductReview productReview = new ProductReview();
        productReview.setProductReviewid(1);
        productReview.setProductid(1);
        productReview.setName("产品1");
        productReview.setReviewdate(new Date());
        productReview.setEmail("rock@163.com");
        productReview.setRating(1);
        productReview.setComments("这是一个注释");
        productReview.setVersion(0);
        productReviewService.save(productReview);

        List<ProductReview> list = productReviewService.list();
        System.out.println(list);
    }

    @Test
    void selectByProductId() {
        ProductReview productReview = productReviewMapper.selectByProductId("1");
        System.out.println(productReview);
    }

}
