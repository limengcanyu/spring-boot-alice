package com.spring.boot.mysql.mongo.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.mysql.mongo.transaction.dao.entity.PlatformSalaryItem;

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
