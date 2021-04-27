package com.spring.boot.mybatisplus.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.mybatisplus.dao.entity.PlatformSalaryItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 平台薪资项定义信息表 Mapper 接口
 * </p>
 *
 * @author rock.jiang
 * @since 2020-09-02
 */
public interface PlatformSalaryItemMapper extends BaseMapper<PlatformSalaryItem> {

    List<Map<String, Object>> queryByColumns(@Param("columns") String columns, @Param("tableName") String tableName, @Param("orders") String orders);

}
