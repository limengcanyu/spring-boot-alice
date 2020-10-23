package com.spring.boot.graphql.provider;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.spring.boot.graphql.datafetchers.GraphQLDataFetchers;
import com.spring.boot.graphql.datafetchers.UserDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/9/26 5:52
 */
@Component
public class GraphQLProvider {

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Autowired
    private UserDataFetcher userDataFetcher;

    private GraphQL graphQL;

    @PostConstruct
    public void init() throws IOException {
        // 方式1
//        URL url = Resources.getResource("schema.graphqls");
//        String sdl = Resources.toString(url, Charsets.UTF_8);
//        GraphQLSchema graphQLSchema = buildSchema(sdl);
//        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();

        // 方式2
        // SDL读取查询类型文件，new SchemaParser().parse(?)解析File、InputStream、String
        // ClassPathResource classPathResource = new ClassPathResource("schema.graphql");
        // TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(classPathResource.getInputStream());
        // 多SDL文件注册
        // ClassPathResource UserSchema = new ClassPathResource("schema/UserSchema.graphql");
        // ClassPathResource schema = new ClassPathResource("schema/QuerySchema.graphql");
        // TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        // SchemaParser schemaParser = new SchemaParser();
        // typeRegistry.merge(schemaParser.parse(UserSchema.getInputStream()));
        // typeRegistry.merge(schemaParser.parse(schema.getInputStream()));
        // 遍历解析目录下的schema，没找到直接获取文件列表的方法
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        SchemaParser schemaParser = new SchemaParser();
        String[] schemaArr = {"BookSchema", "UserSchema", "QuerySchema", "MutationSchema"};
        for (String str : schemaArr) {
            typeRegistry.merge(schemaParser.parse(new ClassPathResource("schema/" + str + ".graphqls").getInputStream()));
        }

        RuntimeWiring runtimeWiring = buildWiring();  // 数据方法对应编写
        SchemaGenerator schemaGenerator = new SchemaGenerator();  // 查询器构建
        // 查询生成
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
//        // 方式1
//        return RuntimeWiring.newRuntimeWiring()
//                .type(newTypeWiring("Query")
//                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
//                .type(newTypeWiring("Book")
//                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
//                .build();

        // 方式2
        return RuntimeWiring.newRuntimeWiring()
                // 查询方法R/get
                .type("Query", builderFunction -> builderFunction
                        .dataFetcher("hello", graphQLDataFetchers.getHelloWorldDataFetcher())
                        .dataFetcher("echo", graphQLDataFetchers.getEchoDataFetcher())

                        .dataFetcher("users", userDataFetcher.getUsersDataFetcher())
                        .dataFetcher("user", userDataFetcher.getUserByIdDataFetcher())

                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher())
                )
                // 级联字段关联查询
                .type("User", builderFunction -> builderFunction.dataFetcher("info", userDataFetcher.getInfoDataFetcher()))
                .type("Book", builderFunction -> builderFunction.dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                // 增改删方法CUD/post/put/del
                .type("Mutation", builderFunction -> builderFunction
                        .dataFetcher("createUser", userDataFetcher.createUserDataFetcher())
                        .dataFetcher("updateUser", userDataFetcher.updateUserDataFetcher())
                        .dataFetcher("deleteUser", userDataFetcher.deleteUserDataFetcher())
                )
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

}
