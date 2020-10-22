package com.spring.boot.graphql.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/9/26 7:49
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/graphql")
public class GraphQLController {

    private final GraphQL graphql;
    private final ObjectMapper objectMapper;

    @Autowired
    public GraphQLController(GraphQL graphql, ObjectMapper objectMapper) {
        this.graphql = graphql;
        this.objectMapper = objectMapper;
    }

    /**
     * GET方式查询
     *
     * @param query         查询语句类json字符串
     * @param operationName 查询操作名称-默认空字符
     * @param variablesJson 查询参数变量-json字符串，默认为{}字符
     * @return map对象
     * @throws IOException json字符串转map对象异常
     */
    @GetMapping
    public Map<String, Object> graphqlGET(@RequestParam("query") String query,
                                          @RequestParam(value = "operationName", defaultValue = "") String operationName,
                                          @RequestParam(value = "variables", defaultValue = "{}") String variablesJson) throws IOException {
        log.debug("graphqlGET query: {}", query);
        Map<String, Object> variables = new LinkedHashMap<>();
        if (variablesJson != null) {
            variables = objectMapper.readValue(variablesJson, new TypeReference<Map<String, Object>>() {
            });
        }
        return executeGraphqlQuery(query, operationName, variables);
    }

    /**
     * POST方式查询
     *
     * @param body json对象方式
     * @return map对象
     */
    @PostMapping
    public Map<String, Object> graphqlPOST(@RequestBody Map<String, Object> body) {
        String query = (String) body.get("query");
        log.debug("graphqlPOST query: {}", query);
        if (query == null) {
            query = "";
        }
        String operationName = (String) body.get("operationName");
        Map<String, Object> variables = (Map<String, Object>) body.get("variables");
        if (variables == null) {
            variables = new LinkedHashMap<>();
        }
        return executeGraphqlQuery(query, operationName, variables);
    }

    /**
     * 执行graphQL查询
     *
     * @param query         查询语句-类json字符
     * @param operationName 查询操作名称-默认空字符
     * @param variables     查询参数变量-map对象、默认为空map
     * @return map对象
     */
    private Map<String, Object> executeGraphqlQuery(String query, String operationName, Map<String, Object> variables) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .operationName(operationName)
                .variables(variables)
                .build();
        return graphql.execute(executionInput).toSpecification();
    }
}
