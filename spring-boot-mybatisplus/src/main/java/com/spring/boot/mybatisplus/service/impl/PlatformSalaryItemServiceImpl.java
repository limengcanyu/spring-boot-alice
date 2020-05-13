package com.spring.boot.mybatisplus.service.impl;

import com.spring.boot.mybatisplus.dao.entity.PlatformSalaryItem;
import com.spring.boot.mybatisplus.dao.mapper.PlatformSalaryItemMapper;
import com.spring.boot.mybatisplus.service.IPlatformSalaryItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台薪资项定义信息表 服务实现类
 * </p>
 *
 * @author rock.jiang
 * @since 2020-05-13
 */
@Service
public class PlatformSalaryItemServiceImpl extends ServiceImpl<PlatformSalaryItemMapper, PlatformSalaryItem> implements IPlatformSalaryItemService {

}
