自定义安全系统

1.以4个项目为例：
spring-boot-security-custom 认证授权系统
spring-boot-cluster1        服务系统1
spring-boot-cluster2        服务系统2
spring-boot-cluster3        服务系统3

2.认证授权系统登录后，在Redis共享Session中存储用户信息，登录其他服务系统时，如服务系统1，其他服务系统根据username从Redis共享Session中
获取用户信息，若查询到，则视为用户已登录，可以访问本系统服务，若未查询到，则视为用户未登录，返回给前端信息表示用户未登陆，让前端跳转到登录页面

3.若多个用户登录时，Redis中会存储各个用户的Session信息，例：Postman登录是一个session，网页登录是一个session





