package com.spring.boot.security.custom.util;

/**
 * <p>Description: </p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/20 0020
 */
public class Book {
    /**
     * 书ID
     */
    private int bookId;
    /**
     * 书作者
     */
    private String author;
    /**
     * 书名
     */
    private String name;
    /**
     * 书价
     */
    private Double price;

    public Book() {
    }

    public Book(int bookId, String author, String name, Double price) {
        this.bookId = bookId;
        this.author = author;
        this.name = name;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
