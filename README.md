# spring-boot-alice

指定参数启动Spring Boot项目

windows中在Git Bash中执行，在Power Shell执行无效

``````bash
# 指定JVM参数和环境变量参数
cd D:\IdeaProjects-MyProject\spring-boot-alice\spring-boot-tomcat
E:/Java/jdk-14.0.2/bin/java -Xms1024m -Xmx1024m -jar -Dserver.port=9000 target/spring-boot-tomcat-1.0-SNAPSHOT.jar

# 指定启动环境
java -jar -Dspring.profiles.active=dev target/spring-boot-tomcat-1.0-SNAPSHOT.jar

# 覆盖启动参数
java -jar -Dserver.port=9000 target/spring-boot-tomcat-1.0-SNAPSHOT.jar


Windows Power Shell中执行

cd D:\IdeaProjects-MyProject\spring-boot-alice\spring-boot-tomcat
E:/Java/jdk-14.0.2/bin/java -Xms1024m -Xmx1024m -jar "-Dserver.port=9000" target/spring-boot-tomcat-1.0-SNAPSHOT.jar

-Dserver.port=9000 参数放在引号中

# 指定启动环境
java -jar "-Dspring.profiles.active=dev" target/spring-boot-tomcat-1.0-SNAPSHOT.jar

# 覆盖启动参数
java -jar "-Dserver.port=9000" target/spring-boot-tomcat-1.0-SNAPSHOT.jar


指定JVM参数启动

java -Xms2048m \
     -Xmx2048m \
     -XX:ReservedCodeCacheSize=512m \
     -XX:SoftRefLRUPolicyMSPerMB=50 \
     -XX:CICompilerCount=4 \
     -XX:+HeapDumpOnOutOfMemoryError \
     -XX:-OmitStackTraceInFastThrow \
     -ea \
     -Dsun.io.useCanonCaches=false \
     -Djdk.http.auth.tunneling.disabledSchemes="" \
     -Djdk.attach.allowAttachSelf=true \
     -Djdk.module.illegalAccess.silent=true \
     -Dkotlinx.coroutines.debug=off \
     -Dide.no.platform.update=true \
     -XX:+UseG1GC \
     -jar xxx.jar

```
