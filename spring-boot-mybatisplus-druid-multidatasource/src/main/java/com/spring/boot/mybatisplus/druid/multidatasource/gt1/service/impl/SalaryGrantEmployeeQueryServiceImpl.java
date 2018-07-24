package com.spring.boot.mybatisplus.druid.multidatasource.gt1.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.boot.mybatisplus.druid.multidatasource.gt1.dao.entity.SalaryGrantEmployee;
import com.spring.boot.mybatisplus.druid.multidatasource.gt1.dao.mapper.SalaryGrantEmployeeMapper;
import com.spring.boot.mybatisplus.druid.multidatasource.gt1.service.SalaryGrantEmployeeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 薪资发放雇员信息查询 服务实现类
 * </p>
 *
 * @author Rock.Jiang
 * @since 2018-04-23
 */
@Service
public class SalaryGrantEmployeeQueryServiceImpl extends ServiceImpl<SalaryGrantEmployeeMapper, SalaryGrantEmployee> implements SalaryGrantEmployeeService {
}
