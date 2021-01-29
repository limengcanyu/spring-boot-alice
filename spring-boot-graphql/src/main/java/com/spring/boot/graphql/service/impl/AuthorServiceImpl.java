package com.spring.boot.graphql.service.impl;

import com.google.common.collect.ImmutableMap;
import com.spring.boot.graphql.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    @Override
    public Map<String, String> queryAuthor(String id) {
        return authors
                .stream()
                .filter(author -> author.get("id").equals(id))
                .findFirst()
                .orElse(null);
    }
}
