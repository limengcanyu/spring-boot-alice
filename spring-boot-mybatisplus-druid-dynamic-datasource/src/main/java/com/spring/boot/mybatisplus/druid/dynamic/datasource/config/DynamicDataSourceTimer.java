//package com.spring.boot.mybatisplus.druid.dynamic.datasource.config;
//
//import javax.sql.DataSource;
//
//public class DynamicDataSourceTimer {
//    /**
//     * 空闲时间周期。超过这个时长没有访问的数据库连接将被释放。默认为10分钟。
//     */
//    private static long idlePeriodTime = 10 * 60 * 1000;
//
//    /**
//     * 动态数据源
//     */
//    private DataSource dds;
//
//    /**
//     * 上一次访问的时间
//     */
//    private long lastUseTime;
//
//    public DynamicDataSourceTimer(DataSource dds) {
//        this.dds = dds;
//        this.lastUseTime = System.currentTimeMillis();
//    }
//
//    /**
//     * 更新最近访问时间
//     */
//    public void refreshTime() {
//        lastUseTime = System.currentTimeMillis();
//    }
//
//    /**
//     * 检测数据连接是否超时关闭。
//     *
//     * @return true-已超时关闭; false-未超时
//     */
//    public boolean checkAndClose() {
//
//        if (System.currentTimeMillis() - lastUseTime > idlePeriodTime) {
//            return true;
//        }
//
//        return false;
//    }
//}
