# spring-boot-mybatisplus

## 事务测试

### 调用自身类的事务

调用者无事务，被调用者有事务
调用者有事务，被调用者有事务

### 调用其它类的事务

调用者无事务，被调用者有事务
调用者有事务，被调用者有事务

Constroller 调用 Service
Service 调用 Service

