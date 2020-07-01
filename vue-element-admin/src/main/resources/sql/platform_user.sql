create table platform_user
(
    id              int auto_increment comment '主键ID'  primary key,
    tenant_id       varchar(20)                          null comment '租户ID',
    username        varchar(50)                          null comment '用户名',
    password        varchar(64)                          null comment '密码',
    user_id         varchar(50)                          null comment '用户ID',
    employee_id     varchar(20)                          null comment '雇员ID',
    avatar          varchar(200)                         null comment '头像',
    email           varchar(50)                          null comment 'email',
    introduction    varchar(255)                         null comment '描述',
    remark          varchar(255)                         null comment '备注',
    active_flag     bit        default b'1'              null comment '是否启用：1-启用；0-禁用',
    creator_id      varchar(20)                          null comment '创建人',
    create_time     datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id      varchar(20)                          null comment '修改人',
    update_time     datetime                             null comment '修改时间',
    version         int        default 0                 null comment '版本号'
) comment '用户表';

create index idx_username_query
    on platform_user (tenant_id, username);

create index idx_user_id_query
    on platform_user (tenant_id, user_id);
