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

## Special behavior inside transactions

### Connection Settings

The MongoDB drivers offer a dedicated replica set name configuration option turing the driver into auto detection mode. 
This option helps identifying replica set master nodes and command routing during a transaction.

Make sure to add replicaSet to the MongoDB URI. Please refer to connection string options for further details.

### Collection Operations

`MongoDB does not support collection operations, such as collection creation, within a transaction. `
This also affects the on the fly collection creation that happens on first usage. 
Therefore make sure to have all required structures in place.

### Transient Errors

MongoDB can add special labels to errors raised during transactional execution. 
Those may indicate transient failures that might vanish by merely retrying the operation. 
We highly recommend Spring Retry for those purposes. 
Nevertheless one may override MongoTransactionManager#doCommit(MongoTransactionObject) to 
implement a Retry Commit Operation behavior as outlined in the MongoDB reference manual.

### Count
   
MongoDB count operates upon collection statistics which may not reflect the actual situation within a transaction. 
The server responds with error 50851 when issuing a count command inside of a multi-document transaction. 
Once MongoTemplate detects an active transaction, all exposed count() methods are converted and 
delegated to the aggregation framework using $match and $count operators, preserving Query settings, such as collation.
   
Restrictions apply when using geo commands inside of the aggregation count helper. 
The following operators cannot be used and must be replaced with a different operator:
   
* $where → $expr
   
* $near → $geoWithin with $center
   
* $nearSphere → $geoWithin with $centerSphere
   
Queries using Criteria.near(…) and Criteria.nearSphere(…) must be rewritten to Criteria.within(…) respective Criteria.withinSphere(…). 
Same applies for the near query keyword in repository query methods that must be changed to within. 
See also MongoDB JIRA ticket DRIVERS-518 for further reference.
   
The following snippet shows count usage inside the session-bound closure:

``````
   session.startTransaction();
   
   template.withSession(session)
       .execute(action -> {
           action.count(query(where("state").is("active")), Step.class)
           ...
``````

The snippet above materializes in the following command:

``````
   db.collection.aggregate(
      [
         { $match: { state: "active" } },
         { $count: "totalEntityCount" }
      ]
   )
``````

instead of:

``````
    db.collection.find( { state: "active" } ).count()
``````

# 容器中部署 mongo replicaSet

spring boot connection

spring.data.mongodb.uri=mongodb://192.168.17.157:30001,192.168.17.157:30002,192.168.17.157:30001/artanis

启动后报以下异常

``````
--2020-04-27 09:09:22.501 - INFO 9124 --- [68.17.157:30001] org.mongodb.driver.cluster                 71 : Monitor thread successfully connected to server with description ServerDescription{address=192.168.17.157:30001, type=REPLICA_SET_PRIMARY, state=CONNECTED, ok=true, version=ServerVersion{versionList=[4, 2, 5]}, minWireVersion=0, maxWireVersion=8, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=11939671, setName='rs', canonicalAddress=172.18.0.2:27017, hosts=[172.18.0.4:27017, 172.18.0.3:27017, 172.18.0.2:27017], passives=[], arbiters=[], primary='172.18.0.2:27017', tagSet=TagSet{[]}, electionId=7fffffff0000000000000001, setVersion=1, lastWriteDate=Mon Apr 27 09:09:14 CST 2020, lastUpdateTimeNanos=64940077353633}
--2020-04-27 09:09:22.504 - INFO 9124 --- [68.17.157:30001] org.mongodb.driver.cluster                 71 : Server 192.168.17.157:30001 is no longer a member of the replica set.  Removing from client view of cluster.
--2020-04-27 09:09:22.504 - INFO 9124 --- [68.17.157:30001] org.mongodb.driver.cluster                 71 : Canonical address 172.18.0.2:27017 does not match server address.  Removing 192.168.17.157:30001 from client view of cluster
--2020-04-27 09:09:39.532 - INFO 9124 --- [72.18.0.4:27017] org.mongodb.driver.cluster                 76 : Exception in monitor thread while connecting to server 172.18.0.4:27017
-
com.mongodb.MongoSocketOpenException: Exception opening socket
	at com.mongodb.internal.connection.SocketStream.open(SocketStream.java:70) ~[mongodb-driver-core-3.11.2.jar:na]
	at com.mongodb.internal.connection.InternalStreamConnection.open(InternalStreamConnection.java:128) ~[mongodb-driver-core-3.11.2.jar:na]
	at com.mongodb.internal.connection.DefaultServerMonitor$ServerMonitorRunnable.run(DefaultServerMonitor.java:117) ~[mongodb-driver-core-3.11.2.jar:na]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_202]
Caused by: java.net.SocketTimeoutException: connect timed out
	at java.net.DualStackPlainSocketImpl.waitForConnect(Native Method) ~[na:1.8.0_202]
	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:85) ~[na:1.8.0_202]
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350) ~[na:1.8.0_202]
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206) ~[na:1.8.0_202]
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188) ~[na:1.8.0_202]
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172) ~[na:1.8.0_202]
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392) ~[na:1.8.0_202]
	at java.net.Socket.connect(Socket.java:589) ~[na:1.8.0_202]
	at com.mongodb.internal.connection.SocketStreamHelper.initialize(SocketStreamHelper.java:64) ~[mongodb-driver-core-3.11.2.jar:na]
	at com.mongodb.internal.connection.SocketStream.initializeSocket(SocketStream.java:79) ~[mongodb-driver-core-3.11.2.jar:na]
	at com.mongodb.internal.connection.SocketStream.open(SocketStream.java:65) ~[mongodb-driver-core-3.11.2.jar:na]
	... 3 common frames omitted

``````

异常信息中 Exception in monitor thread while connecting to server 172.18.0.4:27017 说明 spring boot 项目 从 ServerDescription 描述中获取集群信息，
然后根据描述中的集群信息连接其它 mongo，但是由于是容器内部IP，外部无法访问，因此连接不上，待解决方案



