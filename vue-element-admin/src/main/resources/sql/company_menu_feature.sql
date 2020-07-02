drop table if exists company_menu_feature;

create table company_menu_feature
(
    id               int auto_increment comment '主键ID'      primary key,
    tenant_id        varchar(20)                              null comment '租户ID',
    company_id       varchar(20)                              null comment '公司ID',
    menu_id          varchar(20)                              null comment '菜单ID',
    feature_id       varchar(20)                              null comment '功能ID',
    feature_name     varchar(100)                             null comment '功能名称',

    remark           varchar(255)                             null comment '备注',
    active_flag      tinyint(2) default 1                     null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)                              null comment '创建人ID',
    create_time      datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)                              null comment '修改人ID',
    update_time      datetime   default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int        default 0                     null comment '版本号'
)
    comment '公司菜单功能表';

create index idx_query
    on company_menu_feature (tenant_id, menu_id, feature_id);
