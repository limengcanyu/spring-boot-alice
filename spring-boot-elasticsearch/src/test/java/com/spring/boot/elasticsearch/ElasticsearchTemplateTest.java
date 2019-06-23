package com.spring.boot.elasticsearch;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTemplateTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
}
