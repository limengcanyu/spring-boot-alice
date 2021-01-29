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

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
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

}
