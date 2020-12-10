package com.spring.boot.flaw.service.impl;

import com.spring.boot.flaw.dao.entity.ExampleTable;
import com.spring.boot.flaw.dao.mapper.ExampleTableMapper;
import com.spring.boot.flaw.service.ExampleTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.flaw.utils.SQLInjectionUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class ExampleTableServiceImpl extends ServiceImpl<ExampleTableMapper, ExampleTable> implements ExampleTableService {

    @Resource
    private ExampleTableMapper exampleTableMapper;

    @Override
    public List<ExampleTable> getListByUserId(String userId) {
        return exampleTableMapper.getListByUserId(userId);
    }

    @Override
    public List<ExampleTable> getListByZhName(String zhName) {
//        String legalName = SQLInjectionUtils.replaceIllegalCharacter(zhName);
//        log.debug("合法字符: {}", legalName);
//        legalName = "%" + legalName + "%";
//        return exampleTableMapper.getListByZhName(legalName);

        return exampleTableMapper.getListByZhName(zhName);
    }

    @Override
    public ExampleTable getOneByZhName(String zhName) {
        return exampleTableMapper.getOneByZhName(zhName);
    }
}
