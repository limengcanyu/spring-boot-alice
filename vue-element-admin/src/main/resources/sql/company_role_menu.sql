drop table if exists company_role_menu;

create table company_role_menu
(
    id               int auto_increment comment '主键ID'      primary key,
    tenant_id        varchar(20)                              null comment '租户ID',
    company_id       varchar(20)                              null comment '公司ID',
    role_id          varchar(20)                              null comment '角色ID',
    menu_id          varchar(20)                              null comment '菜单ID',

    remark           varchar(255)                             null comment '备注',
    active_flag      tinyint(2) default 1                     null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)                              null comment '创建人ID',
    create_time      datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)                              null comment '修改人ID',
    update_time      datetime   default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int        default 0                     null comment '版本号'
)
    comment '公司角色菜单表';

create index idx_query
    on company_role_menu (tenant_id, role_id, menu_id);
