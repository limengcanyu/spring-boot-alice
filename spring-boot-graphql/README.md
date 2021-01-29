# spring-boot-graphql

## GraphiQL

localhost:8080

## Playground

localhost:8080/playground

## graphql-voyager

localhost:8080/voyager

## graphql-altair

localhost:8080/altair

# Write your query or mutation here
{
  hello
  echo(toEcho: "SpringBoot")
  
  # 查询user列表
  users {
    id
    password
    username
  }

  # 查询指定用户
  user(id: "user-1") {
    id
    password
    username
    info{
      id
      age
    }
  }
  
  # 查询book
  bookById(id: "book-1") {
    id
    name
    pageCount
    author {
      firstName
      lastName
    }
  }
}

https://github.com/limengcanyu/graphql-spring-boot

## GraphQL Java

https://github.com/graphql-java/graphql-java

This is a GraphQL Java implementation.

## GraphQL Java Tools

https://github.com/graphql-java-kickstart/graphql-java-tools

This library allows you to use the GraphQL schema language to build your graphql-java schema. Inspired by graphql-tools, it parses the given GraphQL schema and allows you to BYOO (bring your own object) to fill in the implementations. GraphQL Java Tools works well if you already have domain POJOs that hold your data (e.g. for RPC, ORM, REST, etc) by allowing you to map these "magically" to GraphQL objects.

GraphQL Java Tools aims for seamless integration with Java, but works for any JVM language. Try it with Kotlin!

## GraphQL-Java Annotations

https://github.com/Enigmatis/graphql-java-annotations

GraphQL-Java is a great library, but its syntax is a little bit verbose. This library offers an annotations-based syntax for GraphQL schema definition.

## schemagen-graphql

https://github.com/bpatters/schemagen-graphql

Java GraphQL Schema Generation and Execution Framework

## GraphQL Spring Boot

Starters

* graphql-spring-boot-starter: turns your Spring Boot application into a GraphQL Server using GraphQL Servlet

* graphiql-spring-boot-starter: embeds the GraphiQL tool for schema introspection and query debugging (see GraphiQL)

* graphql-spring-boot-starter-test: adds testing capabilities to your project, like the @GraphQLTest annotation which is comparable to @SpringBootTest

* voyager-spring-boot-starter: embed the Voyager tool to represent your GraphQL API as an interactive graph (see Voyager)

  https://github.com/APIs-guru/graphql-voyager

## Altair GraphQL Client

https://github.com/imolorhe/altair

Altair is a beautiful feature-rich GraphQL Client IDE for all platforms. Available for MacOS, Windows, Linux, Chrome, Firefox. It enables you interact with any GraphQL server you are authorized to access from any platform you are on.

Much like Postman for GraphQL, you can easily test and optimize your GraphQL implementations.






