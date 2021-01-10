package com.mycompany.bookshop.service.util;

import com.mycompany.bookshop.domain.Book;

import java.math.BigDecimal;

public class BookHelper {
    public Book createBook(long id,String isbn , String title,  String editionYear, BigDecimal price) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEditionYear(Integer.parseInt(editionYear));
        book.setPrice(price.doubleValue());
        return book;
    }
}
