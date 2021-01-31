package com.mycompany.bookshop.domain.util;

import com.mycompany.bookshop.domain.Book;

public class BookHelper {
    public Book buildBook(String isbn, String title, String author, Integer editionYear, Double price) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setEditionYear(editionYear);
        book.setPrice(price);
        return book;
    }

    public Book buildBook(Long id, String isbn, String title, String author, String editionYear, Double price) {
        Book book = new Book();
        book.setId(id);
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setEditionYear(Integer.parseInt(editionYear));
        book.setPrice(price);
        return book;
    }
}
