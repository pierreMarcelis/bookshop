package com.mycompany.bookshop.service.dao;
import com.mycompany.bookshop.domain.Book;
import com.mycompany.bookshop.service.util.BookHelper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;


public class JdbcDao {


    public List<Book> getAllBooks() {
        System.out.println("MySQL JDBC Connection Testing ~");
        List<Book> result = new ArrayList<Book>();
        String SQL_SELECT = "Select * from BOOKS";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/bookshop?useUnicode=true\n" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false\n" +
                "serverTimezone=UTC", "root", "");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String isbn = resultSet.getString("ISBN");
                String title = resultSet.getString("TITLE");
                String editionYear  = resultSet.getString("EDITIONYEAR");
                BigDecimal price = resultSet.getBigDecimal("PRICE");
                BookHelper bookHelper = new BookHelper();
                Book book = bookHelper.createBook(id, isbn, title, editionYear, price);
                result.add(book);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.format("Exception: %s\n%s", e.getMessage());
        }
        return result;
    }
}
