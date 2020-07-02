drop table if exists company_salary_item;

create table company_salary_item
(
    id                               int auto_increment primary key           comment '主键ID',
    tenant_id                        varchar(20)                              null comment '租户ID',
    company_id                       varchar(20)                              null comment '公司ID',
    item_code                        varchar(20)                              null comment '薪资项编码',
    item_name                        varchar(200)                             null comment '薪资项名称',
    item_variant                     varchar(200)                             null comment '薪资项变量',
    item_alias                       varchar(200)                             null comment '薪资项别名',
    item_type                        tinyint  default 0                       null comment '薪资项类型：1-文本项；2-输入项；3-计算项',
    data_type                        tinyint  default 0                       null comment '数据类型：1-文本；2-整数；3-小数',
    data_length                      tinyint                                  null comment '数据长度',
    decimal_digits                   tinyint  default 0                       null comment '小数位数',
    rounding_mode                    tinyint  default 0                       null comment '进位方式：1-四舍五入；2-简单去位',
    input_expression                 varchar(200)                             null comment '录入公式',
    compute_expression               varchar(200)                             null comment '计算公式',
    display_order                    int      default 0                       null comment '显示顺序',
    compute_order                    int      default 0                       null comment '计算顺序',
    sick_leave_deduct_base_item      tinyint  default 0                       null comment '病事假扣款基数项：1-是；0-否',
    overtime_salary_base_item        tinyint  default 0                       null comment '加班工资基数项：1-是；0-否',
    adjust_salary_base_item          tinyint  default 0                       null comment '调整工资基数项：1-是；0-否',
    item_source                      tinyint  default 1                       not null comment '薪资项来源：1-平台薪资项；2-自定义薪资项',
    into_income                      tinyint  default 0                       not null comment '计入所得项收入：1-是；0-否',

    remark                           varchar(255)                             null comment '备注',
    active_flag                      tinyint(2) default 1                     null comment '是否启用：1-启用；0-禁用',
    creator_id                       varchar(20)                              null comment '创建人ID',
    create_time                      datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id                       varchar(20)                              null comment '修改人ID',
    update_time                      datetime   default CURRENT_TIMESTAMP not null comment '修改时间',
    version                          int        default 0                     null comment '版本号'
)
    comment '公司薪资项表';

create index idx_item_code
    on company_salary_item (tenant_id, company_id, item_code);
