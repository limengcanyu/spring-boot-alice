CREATE TABLE tenant_user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    tenant_id VARCHAR(20) NULL DEFAULT NULL COMMENT '租户ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);

INSERT INTO tenant_user (id, tenant_id, name, age, email) VALUES
(1, 'tenant_000001', 'Jone', 18, 'test1@baomidou.com'),
(2, 'tenant_000002', 'Jack', 20, 'test2@baomidou.com'),
(3, 'tenant_000003', 'Tom', 28, 'test3@baomidou.com'),
(4, 'tenant_000004', 'Sandy', 21, 'test4@baomidou.com'),
(5, 'tenant_000005', 'Billie', 24, 'test5@baomidou.com');

