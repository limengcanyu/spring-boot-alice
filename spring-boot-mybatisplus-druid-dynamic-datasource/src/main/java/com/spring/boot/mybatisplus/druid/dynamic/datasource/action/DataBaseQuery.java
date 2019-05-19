package com.spring.boot.mybatisplus.druid.dynamic.datasource.action;

import com.spring.boot.mybatisplus.druid.dynamic.datasource.config.ExtendSource;

import java.util.Map;
import java.util.Optional;

/**
 * @FileName: DataBaseQuery.java
 * @Description: DataBaseQuery.java类说明
 * @Author: tao.shi
 * @Date: 2019/2/26 11:44
 */
public interface DataBaseQuery {

    /**
     * 具体的sql查询
     * @param extendSource
     * @param sql
     * @return
     */
    Optional<Map<String,Object>> queryOradd(ExtendSource extendSource, String sql);

}
