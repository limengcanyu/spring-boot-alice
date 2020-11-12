# create schema if not exists artanis;

create table if not exists artanis.platform_salary_item
(
    id                  int auto_increment comment '主键ID'
        primary key,
    item_code           varchar(20)                          null comment '薪资项编码',
    item_name           varchar(200)                         null comment '薪资项名称',
    item_variant        varchar(200)                         null comment '薪资项变量',
    item_alias          varchar(200)                         null comment '薪资项别名',
    item_type           tinyint(2) default 0                 null comment '薪资项类型：1-文本项；2-输入项；3-计算项',
    data_type           tinyint(2) default 0                 null comment '数据类型：1-文本；2-整数；3-小数',
    data_length         tinyint(2)                           null comment '数据长度',
    decimal_digits      tinyint(2) default 0                 null comment '小数位数',
    rounding_mode       tinyint(2) default 0                 null comment '进位方式：1-四舍五入；2-简单去位',
    original_expression varchar(200)                         null comment '原始公式',
    compute_expression  varchar(200)                         null comment '计算公式',
    display_order       int        default 0                 null comment '显示顺序',
    compute_order       int        default 0                 null comment '计算顺序',
    remark              varchar(255)                         null comment '备注 ',
    is_active           bit        default b'1'              null comment '是否启用：1-启用；0-禁用',
    creator_id          varchar(20)                          null comment '创建人',
    create_time         datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    modifier_id         varchar(20)                          null comment '修改人',
    modify_time         datetime                             null comment '修改时间',
    version             int        default 0                 null comment '版本号'
)
    comment '平台薪资项定义信息表';

create index idx_item_code
    on artanis.platform_salary_item (item_code);

create table if not exists artanis.table_name
(
    column_1 int auto_increment
        primary key,
    column_2 int null,
    column_3 int null,
    constraint table_name_column_2_uindex
        unique (column_2)
);

