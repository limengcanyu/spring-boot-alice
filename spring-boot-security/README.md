前后端分离的Spring Security后端项目

Postman中
登录url如下，可以是POST,GET请求，设置2个参数:username, password
http://localhost:8080/login?username=rock&password=123456

查询当前时间url如下，可以是POST,GET请求
localhost:8080/time/selectTime

登出url如下，可以是POST,GET请求
localhost:8080/logout


使用Redis Spring Session 替换 Web 容器的HttpSession
1. how to use Spring Session to transparently leverage Redis to back a web application’s HttpSession when using Spring Boot

1.1. You should now see a message indicating your are logged in with the user entered previously. The user’s information is stored in Redis rather than Tomcat’s HttpSession implementation.

1.2. How does it work?
Instead of using Tomcat’s HttpSession, we are actually persisting the values in Redis. Spring Session replaces the HttpSession with an implementation that is backed by Redis. When Spring Security’s SecurityContextPersistenceFilter saves the SecurityContext to the HttpSession it is then persisted into Redis.

When a new HttpSession is created, Spring Session creates a cookie named SESSION in your browser that contains the id of your session. Go ahead and view the cookies (click for help with Chrome or Firefox).



