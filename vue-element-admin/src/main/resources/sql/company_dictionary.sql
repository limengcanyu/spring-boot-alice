drop table if exists company_dictionary;

create table company_dictionary
(
    id               int auto_increment comment '主键ID'      primary key,
    tenant_id        varchar(20)                              null comment '租户ID',
    company_id       varchar(20)                              null comment '公司ID',
    field_code       varchar(100)                             null comment '字段编码',
    field_name       varchar(100)                             null comment '字段名称',
    field_value      int                                      null comment '字段取值',
    field_label      varchar(100)                             null comment '字段标签',
    field_order      int(8)                                   null comment '字段顺序',

    remark           varchar(255)                             null comment '备注',
    active_flag      tinyint(2) default 1                     null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)                              null comment '创建人ID',
    create_time      datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)                              null comment '修改人ID',
    update_time      datetime   default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int        default 0                     null comment '版本号'
)
    comment '公司字典表';

create index idx_query
    on company_dictionary (tenant_id, field_code);
