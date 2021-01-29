package com.spring.boot.graphql.java.kickstart;

import graphql.Scalars;
import graphql.language.FieldDefinition;
import graphql.language.ListType;
import graphql.language.ObjectTypeDefinition;
import graphql.schema.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringBootGraphqlJavaKickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraphqlJavaKickstartApplication.class, args);
    }

    @Bean
    GraphQLSchema schema() {
        DataFetcher<String> test = env -> "response";
        DataFetcher<String> hello = env -> "jessica";

        GraphQLFieldDefinition testFieldDefinition = GraphQLFieldDefinition.newFieldDefinition().name("test")
                .type(Scalars.GraphQLString).build();

        GraphQLFieldDefinition helloFieldDefinition = GraphQLFieldDefinition.newFieldDefinition().name("hello")
                .type(Scalars.GraphQLString).build();

//        List<FieldDefinition> fieldDefinitions = new ArrayList<>();
//        FieldDefinition name = FieldDefinition.newFieldDefinition().name("name").type().build();
//        fieldDefinitions.add(name);
//        FieldDefinition age = FieldDefinition.newFieldDefinition().name("age").type(ListType.newListType().build()).build();
//        fieldDefinitions.add(name);
//
//        ObjectTypeDefinition definition = ObjectTypeDefinition.newObjectTypeDefinition().name("user").fieldDefinitions(fieldDefinitions).build();

        return GraphQLSchema.newSchema()
                .query(GraphQLObjectType.newObject()
                        .name("query")
                        .field(testFieldDefinition)
                        .field(helloFieldDefinition)
//                        .definition(definition)
                        .build())
                .codeRegistry(GraphQLCodeRegistry.newCodeRegistry()
                        .dataFetcher(FieldCoordinates.coordinates("query", "test"), test)
                        .dataFetcher(FieldCoordinates.coordinates("query", "hello"), hello)
//                        .dataFetcher(FieldCoordinates.coordinates("query", "user"), hello)
                        .build())
                .build();
    }
}
