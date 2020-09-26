# spring-boot-graphql

## index页面

localhost:8080

## playground

localhost:8080/playground

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