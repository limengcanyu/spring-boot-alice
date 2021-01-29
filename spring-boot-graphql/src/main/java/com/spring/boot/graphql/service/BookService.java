package com.spring.boot.graphql.service;

import java.util.Map;

public interface BookService {
    Map<String, String> queryBook(String id);
}
