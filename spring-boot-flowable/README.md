# spring-boot-flowable

## docker

docker run --name flowable -p 8080:8080 -d flowable/all-in-one:6.5.0

Include Flowable apps

Flowable IDM 

http://192.168.31.149:8080/flowable-idm

Flowable Modeler

http://192.168.31.149:8080/flowable-modeler

Flowable Task

http://192.168.31.149:8080/flowable-task

Flowable Admin

http://192.168.31.149:8080/flowable-admin

Default user
user: admin
password: test


## Spring Boot - Flowable integration

``````
<dependency>
    <groupId>org.flowable</groupId>
    <artifactId>flowable-spring-boot-starter</artifactId>
    <version>${flowable.version}</version>
</dependency>
``````





