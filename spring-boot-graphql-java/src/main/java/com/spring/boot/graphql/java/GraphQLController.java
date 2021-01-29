//package com.spring.boot.graphql.java;
//
//import graphql.GraphQL;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
///**
// * <p>Description: </p>
// *
// * @author rock.jxf
// * @date 2020/9/26 7:49
// */
//@Slf4j
//@CrossOrigin
//@RestController
//@RequestMapping("/graphql")
//public class GraphQLController {
//
//    @Autowired
//    private GraphQL graphql;
//
//    @RequestMapping("query")
//    public Map<String, Object> graphqlGET(@RequestParam("query") String query) {
//        log.debug("query: {}", query);
//        return graphql.execute(query).toSpecification();
//    }
//}
