drop table if exists tenant_dictionary;
create table tenant_dictionary
(
    id               int auto_increment                              comment '主键ID' primary key,
    tenant_id        varchar(20)                            not null comment '租户ID',
    company_id       varchar(20)                            not null comment '公司ID',

    field_code       varchar(100)                           not null comment '字段编码',
    field_name       varchar(100)                           not null comment '字段名称',
    field_value      int                                    not null comment '字段取值',
    field_label      varchar(100)                           not null comment '字段标签',
    field_order      int(8)                                 not null comment '字段顺序',

    remark           varchar(255) default ''                    null comment '备注',
    active_flag      tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)  default ''                    null comment '创建人ID',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)  default ''                    null comment '修改人ID',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int          default 0                 not null comment '版本号'
) comment '租户字典表';

create index idx_query on tenant_dictionary (tenant_id, field_code);


drop table if exists tenant_menu;
create table tenant_menu
(
    id               int auto_increment                              comment '主键ID' primary key,
    tenant_id        varchar(20)                            not null comment '租户ID',
    company_id       varchar(20)                            not null comment '公司ID',

    menu_id          varchar(20)                            not null comment '菜单ID',
    menu_name        varchar(100)                           not null comment '菜单名称',
    superior_menu_id varchar(20)  default ''                    null comment '上级菜单ID',
    router_path      varchar(100)                           not null comment '路由PATH',

    remark           varchar(255) default ''                    null comment '备注',
    active_flag      tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)  default ''                    null comment '创建人ID',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)  default ''                    null comment '修改人ID',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int          default 0                 not null comment '版本号'
) comment '租户菜单表';

create index idx_query on tenant_menu (tenant_id, menu_id);


drop table if exists tenant_menu_feature;
create table tenant_menu_feature
(
    id               int auto_increment                              comment '主键ID' primary key,
    tenant_id        varchar(20)                            not null comment '租户ID',
    company_id       varchar(20)                            not null comment '公司ID',

    menu_id          varchar(20)                            not null comment '菜单ID',
    feature_id       varchar(20)                            not null comment '功能ID',
    feature_name     varchar(100)                           not null comment '功能名称',

    remark           varchar(255) default ''                    null comment '备注',
    active_flag      tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)  default ''                    null comment '创建人ID',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)  default ''                    null comment '修改人ID',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int          default 0                 not null comment '版本号'
) comment '租户菜单功能表';

create index idx_query on tenant_menu_feature (tenant_id, menu_id, feature_id);


drop table if exists tenant_role;
create table tenant_role
(
    id               int auto_increment                              comment '主键ID' primary key,
    tenant_id        varchar(20)                            not null comment '租户ID',
    company_id       varchar(20)                            not null comment '公司ID',

    role_id          varchar(20)                            not null comment '角色ID',
    role_name        varchar(100)                           not null comment '角色名称',
    superior_role_id varchar(20)  default ''                    null comment '上级角色ID',

    remark           varchar(255) default ''                    null comment '备注',
    active_flag      tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)  default ''                    null comment '创建人ID',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)  default ''                    null comment '修改人ID',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int          default 0                 not null comment '版本号'
) comment '租户角色表';

create index idx_query on tenant_role (tenant_id, role_id);


drop table if exists tenant_role_menu;
create table tenant_role_menu
(
    id               int auto_increment                              comment '主键ID' primary key,
    tenant_id        varchar(20)                            not null comment '租户ID',
    company_id       varchar(20)                            not null comment '公司ID',

    role_id          varchar(20)                            not null comment '角色ID',
    menu_id          varchar(20)                            not null comment '菜单ID',

    remark           varchar(255) default ''                    null comment '备注',
    active_flag      tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)  default ''                    null comment '创建人ID',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)  default ''                    null comment '修改人ID',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int          default 0                 not null comment '版本号'
) comment '租户角色菜单表';

create index idx_query on tenant_role_menu (tenant_id, role_id, menu_id);


drop table if exists tenant_salary_item;
create table tenant_salary_item
(
    id                          int auto_increment                              comment '主键ID' primary key,
    tenant_id                   varchar(20)                            not null comment '租户ID',
    company_id                  varchar(20)                            not null comment '公司ID',

    item_code                   varchar(20)                            not null comment '薪资项编码',
    item_name                   varchar(200)                           not null comment '薪资项名称',
    item_variant                varchar(200)                           not null comment '薪资项变量',
    item_alias                  varchar(200)                           not null comment '薪资项别名',
    item_type                   tinyint      default 0                 not null comment '薪资项类型：1-文本项；2-输入项；3-计算项',
    data_type                   tinyint      default 0                 not null comment '数据类型：1-文本；2-整数；3-小数',
    data_length                 tinyint      default 12                not null comment '数据长度',
    decimal_digits              tinyint      default 0                 not null comment '小数位数',
    rounding_mode               tinyint      default 0                 not null comment '进位方式：1-四舍五入；2-简单去位',
    input_expression            varchar(200) default ''                    null comment '录入公式',
    compute_expression          varchar(200) default ''                    null comment '计算公式',
    display_order               int          default 0                 not null comment '显示顺序',
    compute_order               int          default 0                     null comment '计算顺序',
    sick_leave_deduct_base_item tinyint      default 0                     null comment '病事假扣款基数项：1-是；0-否',
    overtime_salary_base_item   tinyint      default 0                     null comment '加班工资基数项：1-是；0-否',
    adjust_salary_base_item     tinyint      default 0                     null comment '调整工资基数项：1-是；0-否',
    item_source                 tinyint      default 1                 not null comment '薪资项来源：1-平台薪资项；2-自定义薪资项',
    into_income                 tinyint      default 0                 not null comment '计入所得项收入：1-是；0-否',

    remark                      varchar(255) default ''                    null comment '备注',
    active_flag                 tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id                  varchar(20)  default ''                    null comment '创建人ID',
    create_time                 datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id                  varchar(20)  default ''                    null comment '修改人ID',
    update_time                 datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version                     int          default 0                 not null comment '版本号'
) comment '租户薪资项表';

create index idx_item_code on tenant_salary_item (tenant_id, company_id, item_code);


drop table if exists tenant_user;
create table tenant_user
(
    id              int auto_increment                               comment '主键ID' primary key,
    tenant_id       varchar(20)                             not null comment '租户ID',
    company_id      varchar(20)                             not null comment '公司ID',

    username        varchar(50)                             not null comment '用户名',
    password        varchar(64)                             not null comment '密码',
    user_id         varchar(50)                             not null comment '用户ID',
    employee_id     varchar(20)   default ''                    null comment '雇员ID',
    avatar          varchar(200)  default ''                    null comment '头像',
    email           varchar(50)   default ''                    null comment 'email',
    introduction    varchar(255)  default ''                    null comment '描述',

    remark           varchar(255) default ''                    null comment '备注',
    active_flag      tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)  default ''                    null comment '创建人ID',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)  default ''                    null comment '修改人ID',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int          default 0                 not null comment '版本号'
) comment '租户用户表';

create index idx_username_query on tenant_user (tenant_id, username);

create index idx_user_id_query on tenant_user (tenant_id, user_id);


drop table if exists tenant_user_role;
create table tenant_user_role
(
    id               int auto_increment                              comment '主键ID' primary key,
    tenant_id        varchar(20)                            not null comment '租户ID',
    company_id       varchar(20)                            not null comment '公司ID',

    user_id          varchar(20)                            not null comment '用户ID',
    role_id          varchar(20)                            not null comment '角色ID',

    remark           varchar(255) default ''                    null comment '备注',
    active_flag      tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)  default ''                    null comment '创建人ID',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)  default ''                    null comment '修改人ID',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int          default 0                 not null comment '版本号'
) comment '租户用户角色表';

create index idx_query on tenant_user_role (tenant_id, user_id, role_id);


create table tenant
(
    id               int auto_increment                              comment '主键ID' primary key,
    tenant_id        varchar(20)                            not null comment '租户ID',

    zh_name          varchar(100) default ''                    null comment '中文名称',
    eng_name         varchar(200) default ''                    null comment '英文名称',
    description      varchar(200) default ''                    null comment '描述',

    remark           varchar(255) default ''                    null comment '备注',
    active_flag      tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id       varchar(20)  default ''                    null comment '创建人ID',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id       varchar(20)  default ''                    null comment '修改人ID',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version          int          default 0                 not null comment '版本号'
) comment '租户表';

create index idx_query on tenant (tenant_id);


create table tenant_company
(
    id                         int auto_increment                              comment '主键ID' primary key,
    tenant_id                  varchar(20)                            not null comment '租户ID',
    company_id                 varchar(20)                            not null comment '公司ID',

    zh_name                    varchar(100) default ''                    null comment '中文名称',
    eng_name                   varchar(200) default ''                    null comment '英文名称',
    legal_representative       varchar(20)                                null comment '企业法人',
    uniform_social_credit_code varchar(30)                                null comment '社会统一信用代码',
    description                varchar(200) default ''                    null comment '描述',

    remark                     varchar(255) default ''                    null comment '备注',
    active_flag                tinyint(2)   default 1                 not null comment '是否启用：1-启用；0-禁用',
    creator_id                 varchar(20)  default ''                    null comment '创建人ID',
    create_time                datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id                 varchar(20)  default ''                    null comment '修改人ID',
    update_time                datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    version                    int          default 0                 not null comment '版本号'
) comment '租户公司表';

create index idx_query on tenant_company (tenant_id, company_id);
