package com.spring.boot.mybatisplus.service.impl;

import com.spring.boot.mybatisplus.dao.entity.PlatformSalaryItem;
import com.spring.boot.mybatisplus.dao.mapper.PlatformSalaryItemMapper;
import com.spring.boot.mybatisplus.service.IPlatformSalaryItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 平台薪资项定义信息表 服务实现类
 * </p>
 *
 * @author rock.jiang
 * @since 2020-09-02
 */
@Service
public class PlatformSalaryItemServiceImpl extends ServiceImpl<PlatformSalaryItemMapper, PlatformSalaryItem> implements IPlatformSalaryItemService {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addItem() throws Exception {

        try {
            PlatformSalaryItem item = new PlatformSalaryItem();
            item.setItemCode("item_134");
            item.setItemName("item_134");
            save(item);

            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
