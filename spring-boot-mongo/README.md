# spring-boot-mongo

## 开启 mongo 事务支持

1. 添加 @EnableTransactionManagement 注解
2. 配置 MongoTransactionManager

``````
    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
``````
