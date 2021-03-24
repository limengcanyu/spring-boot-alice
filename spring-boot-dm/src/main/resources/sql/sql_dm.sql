CREATE TABLE CMDB2.PRODUCT_REVIEW
(
    ID               INT                    IDENTITY(1,1),
    PRODUCT_REVIEWID INT          NOT NULL,
    PRODUCTID        INT          NOT NULL,
    NAME             VARCHAR(50)  NOT NULL,
    REVIEWDATE       DATE         NOT NULL,
    EMAIL            VARCHAR(50)  NOT NULL,
    RATING           INT          NOT NULL,
    COMMENTS         TEXT,
    VERSION          INT          NOT NULL,
    PRIMARY KEY(ID)
);

COMMENT ON COLUMN CMDB2.PRODUCT_REVIEW.ID IS '主键ID';

COMMENT ON COLUMN CMDB2.PRODUCT_REVIEW.PRODUCT_REVIEWID IS '产品审查ID';

COMMENT ON COLUMN CMDB2.PRODUCT_REVIEW.PRODUCTID IS '产品ID';

drop table CMDB.ALIYUNDISK cascade;
drop table CMDB.ALIYUNEC2INSTANCE cascade;

select * from SYSOBJECTS;

select * from ALL_ALL_TABLES where owner = 'CMDB';

select * from ALL_USERS;




