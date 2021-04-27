package com.spring.boot.mybatisplus.service;

import com.spring.boot.mybatisplus.dao.entity.PlatformSalaryItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 平台薪资项定义信息表 服务类
 * </p>
 *
 * @author rock.jiang
 * @since 2020-09-02
 */
public interface IPlatformSalaryItemService extends IService<PlatformSalaryItem> {
    boolean addItem() throws Exception;

    boolean updateItem() throws Exception;
}
