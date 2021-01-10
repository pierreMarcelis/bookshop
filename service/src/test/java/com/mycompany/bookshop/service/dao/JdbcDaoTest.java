package com.mycompany.bookshop.service.dao;

import com.mycompany.bookshop.domain.Book;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;

import static org.junit.Assert.fail;

public class JdbcDaoTest {
    private  JdbcDao jdbcDao;

    @Before
    public void init() {
        jdbcDao= new JdbcDao();
    }


    @Test
    public void testGetAllBooks()  {
        try {
            List<Book> books = jdbcDao.getAllBooks();
            assertThat(books, is(notNullValue()));

         } catch (Exception e) {
            fail("Unable to reach the database : " + e.getMessage());
        }
    }


}
