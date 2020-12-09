package com.spring.boot.hole.service.impl;

import com.spring.boot.hole.dao.entity.ExampleTable;
import com.spring.boot.hole.dao.mapper.ExampleTableMapper;
import com.spring.boot.hole.service.ExampleTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rock.jiang
 * @since 2020-12-09
 */
@Service
public class ExampleTableServiceImpl extends ServiceImpl<ExampleTableMapper, ExampleTable> implements ExampleTableService {

    @Resource
    private ExampleTableMapper exampleTableMapper;

    @Override
    public List<ExampleTable> getListByColumn1Name(String userId) {
        return exampleTableMapper.getListByColumn1Name(userId);
    }
}
