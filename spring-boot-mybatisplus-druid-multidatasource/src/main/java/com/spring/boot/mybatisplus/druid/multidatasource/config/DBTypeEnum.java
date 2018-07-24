package com.spring.boot.mybatisplus.druid.multidatasource.config;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/5/24 0024
 */
public enum DBTypeEnum {
    dbArtanis("dbArtanis"), dbSamuro("dbSamuro"), dbGt1("dbGt1");

    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
