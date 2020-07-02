package com.vue.element.admin.mybatisplus.service.impl;

import com.vue.element.admin.mybatisplus.entity.CompanyUser;
import com.vue.element.admin.mybatisplus.mapper.CompanyUserMapper;
import com.vue.element.admin.mybatisplus.service.ICompanyUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司用户表 服务实现类
 * </p>
 *
 * @author rock.jiang
 * @since 2020-07-02
 */
@Service
public class CompanyUserServiceImpl extends ServiceImpl<CompanyUserMapper, CompanyUser> implements ICompanyUserService {

}
