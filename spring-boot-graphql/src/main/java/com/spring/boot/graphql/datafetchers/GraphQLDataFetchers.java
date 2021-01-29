package com.spring.boot.graphql.datafetchers;

import com.spring.boot.graphql.service.AuthorService;
import com.spring.boot.graphql.service.BookService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/9/26 5:57
 */
@Component
public class GraphQLDataFetchers {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    /**
     * 输出world
     *
     * @return 返回字符串
     */
    public DataFetcher getHelloWorldDataFetcher() {
        return environment -> "world";
    }

    /**
     * 参数直接输出
     *
     * @return 返回字符串
     */
    public DataFetcher getEchoDataFetcher() {
        return environment -> environment.getArgument("toEcho");
    }


    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookService.queryBook(bookId);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authorService.queryAuthor(authorId);
        };
    }
}
