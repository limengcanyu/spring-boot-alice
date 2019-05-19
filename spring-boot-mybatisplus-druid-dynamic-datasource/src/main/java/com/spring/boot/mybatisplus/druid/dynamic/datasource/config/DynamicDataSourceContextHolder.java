//package com.spring.boot.mybatisplus.druid.dynamic.datasource.config;
//
///**
// * <p>Description: </p>
// *
// * @author Rock Jiang
// * @version 1.0
// * @date 2018/5/24 0024
// */
//public class DynamicDataSourceContextHolder {
//    private static final ThreadLocal<String> dataSourceKeyHolder = new ThreadLocal<>();
//
//    /**
//     * To switch DataSource
//     *
//     * @param key the key
//     */
//    public static void setDataSourceKey(String key) {
//        dataSourceKeyHolder.set(key);
//    }
//
//    /**
//     * Get current DataSource
//     *
//     * @return data source key
//     */
//    public static String getDataSourceKey() {
//        return dataSourceKeyHolder.get();
//    }
//
//    /**
//     * To set DataSource as default
//     */
//    public static void clearDataSourceKey() {
//        dataSourceKeyHolder.remove();
//    }
//}
