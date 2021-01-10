package com.mycompany.bookshop.exporter;

import com.mycompany.bookshop.domain.Book;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public  class DataExcellReaderTest {


    @Test
    public void testReadExcelDataMapping()  {
        try {
            List<Book> books = DataExcellReader.readExcelData("MybookShop.xlsx");
            assertThat(books.size(), is(3));

            Book book = books.get(0);

            assertThat(book.getIsbn(), is("978-2263-16102-5"));
            assertThat(book.getTitle(), is("L'atlas du vélo"));
            assertThat(book.getAuthor(), is("Claude Droussent"));
            assertThat(book.getEditionYear(), is(2019));
            assertThat(book.getPrice(), is(Double.parseDouble("24,9")));

        } catch (Exception e) {
            fail("Unable to load testcase");
        }
    }

    /*
    @Test
    public void testIsFormatedDate() {
                assertThat(isFormatedDate("01-07-13"), is(true));
                assertThat(isFormatedDate(""), is(false));
                assertThat(isFormatedDate(null), is(false));
   }*/
}