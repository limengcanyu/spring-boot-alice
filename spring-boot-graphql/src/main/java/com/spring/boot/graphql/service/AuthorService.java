package com.spring.boot.graphql.service;

import java.util.Map;

public interface AuthorService {
    Map<String, String> queryAuthor(String id);
}
