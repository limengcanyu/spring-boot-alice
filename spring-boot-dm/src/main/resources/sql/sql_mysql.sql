CREATE TABLE CMDB2.PRODUCT_REVIEW
(
    ID               INT          AUTO_INCREMENT  COMMENT '主键ID',
    PRODUCT_REVIEWID INT          NOT NULL  COMMENT '产品审查ID',
    PRODUCTID        INT          NOT NULL  COMMENT '产品ID',
    NAME             VARCHAR(50)  NOT NULL  COMMENT '产品名称',
    REVIEWDATE       DATE         NOT NULL  COMMENT '审查日期',
    EMAIL            VARCHAR(50)  NOT NULL  COMMENT 'EMAIL',
    RATING           INT          NOT NULL  COMMENT '良品率',
    COMMENTS         TEXT                   COMMENT '说明',
    VERSION          INT          NOT NULL  COMMENT '版本号',
    PRIMARY KEY(ID)
);
