package com.spring.boot.graphql.service.impl;

import com.google.common.collect.ImmutableMap;
import com.spring.boot.graphql.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    @Override
    public Map<String, String> queryBook(String id) {
        return books
                .stream()
                .filter(book -> book.get("id").equals(id))
                .findFirst()
                .orElse(null);
    }
}
