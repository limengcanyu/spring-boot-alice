# spring-boot-mybatisplus

数据库密码加密
数据库密码直接写在配置中，对运维安全来说，是一个很大的挑战。Druid为此提供一种数据库密码加密的手段ConfigFilter。

2.1 执行命令加密数据库密码
在命令行中执行如下命令：

```
java -cp C:/Users/EDZ/.m2/repository/com/alibaba/druid/1.2.3/druid-1.2.3.jar com.alibaba.druid.filter.config.ConfigTools you_password

java -cp C:/Users/EDZ/.m2/repository/com/alibaba/druid/1.2.3/druid-1.2.3.jar com.alibaba.druid.filter.config.ConfigTools .PassW0rd,321

输出

PS C:\Users\EDZ> java -cp C:/Users/EDZ/.m2/repository/com/alibaba/druid/1.2.3/druid-1.2.3.jar com.alibaba.druid.filter.config.ConfigTools .PassW0rd,321
privateKey:MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAxrLVO2U6GxkaDSwTOmI4onB6th0igNhokroBZ6FQxbAzscY4ecdvaYD82+TlC4p7LwmT1K9pzJcLlRFTLI7eDwIDAQABAkBi5ihUQ/JEAp6A5+s6KCFgWPP7KU0/LHgAcCy61ARMIKNVYVW1zmYRw0uyYZjevGLCnkBtftHZTLrkt96ztdgBAiEA4lKecdVfQ5T31P2L5gFVSE0dQCVyteJ7XUc8m1A2NwECIQDgwOd2GP7CMhKT19biGBK4Bdvai60XLKNy5j9sZe6lDwIhAJ+lMdkQbxuH+G4Iz+dV52eR5eE6yTjd4rGAr5LLOQMBAiEAvQvlZSPPHd9qWusBNzoSfLGI5QH5vPVyIlJjZLU0j6cCIQCnkwhniI/qylcdOjoVLXPw5ZEhlPAr7sOlduuGUfEdMg==
publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMay1TtlOhsZGg0sEzpiOKJwerYdIoDYaJK6AWehUMWwM7HGOHnHb2mA/Nvk5QuKey8Jk9SvacyXC5URUyyO3g8CAwEAAQ==
password:oLVeTenminhLb/2r22YO1oq8XYvasj+ez4iizznN7Yel9ySvXS5kBw2xf19CJaKA6imVNHMvDGMShrQ0H1xoMQ==
PS C:\Users\EDZ>

```

输入你的数据库密码，输出的是加密后的结果。

配置

```
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
# 加密后的密码（原密码 123456）
spring.datasource.password=WVMjPhfXQrIsWRo0/RCqAVvYtTU9WNVToKJohb8AlUmHwnV6vwFL+FM2CNFDMJwGHW1iCmyaUlF+sgvFdogqEA==
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
# 公钥
publickey=MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIiwHpFrDijV+GzwRTzWJk8D3j3jFfhsMFJ/7k1NTvBuLgL+TdIHgaMNOIEjHpXzuvX38J3FtOK8hLrySncVGOMCAwEAAQ==
# 配置 connection-properties，启用加密，配置公钥。
spring.datasource.druid.connection-properties=config.decrypt=true;config.decrypt.key=${publickey}
# 启动ConfigFilter
spring.datasource.druid.filter.config.enabled=true

```
