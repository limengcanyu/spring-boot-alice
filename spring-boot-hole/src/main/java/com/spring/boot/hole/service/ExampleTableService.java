package com.spring.boot.hole.service;

import com.spring.boot.hole.dao.entity.ExampleTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rock.jiang
 * @since 2020-12-09
 */
public interface ExampleTableService extends IService<ExampleTable> {
    List<ExampleTable> getListByColumn1Name(String userId);
}
