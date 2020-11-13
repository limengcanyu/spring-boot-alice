package com.spring.boot.security.custom.jackson;

import com.spring.boot.security.custom.util.Book;
import com.spring.boot.security.custom.util.JacksonUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/20 0020
 */
public class JacksonUtilsTest {
    @Test
    public void bean2JsonTest() {
        Book book = new Book(1, "rock", "大秦帝国", 200.03);
        System.out.println("book: " + book);

        String bookStr = JacksonUtils.bean2Json(book);
        System.out.println("bookStr: " + bookStr);

        Book retBook = JacksonUtils.json2Bean(bookStr, Book.class);
        System.out.println("retBook: " + retBook);

        System.out.println("-------------------------------------------");

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "rock", "大秦帝国", 200.03));
        bookList.add(new Book(2, "鬼谷子", "大秦帝国", 300.03));
        bookList.add(new Book(3, "墨子", "大秦帝国", 400.03));
        System.out.println("bookList: " + bookList);

        String bookListStr = JacksonUtils.bean2Json(bookList);
        System.out.println("bookListStr: " + bookListStr);

        List<Book> retBookList = JacksonUtils.json2Bean(bookListStr, List.class);
        System.out.println("retBookList: " + retBookList);

        System.out.println("-------------------------------------------");

        Map<String, Book> bookMap = new HashMap<>();
        bookMap.put("1", new Book(1, "rock", "大秦帝国", 200.03));
        bookMap.put("2", new Book(1, "rock", "大秦帝国", 200.03));
        bookMap.put("3", new Book(1, "rock", "大秦帝国", 200.03));
        System.out.println("bookMap: " + bookMap);

        String bookMapStr = JacksonUtils.bean2Json(bookMap);
        System.out.println("bookMapStr: " + bookMapStr);

        Map<String, Book> retBookMap = JacksonUtils.json2Bean(bookMapStr, Map.class);
        System.out.println("retBookMap: " + retBookMap);
    }
}
