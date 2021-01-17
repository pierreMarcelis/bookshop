package com.mycompany.bookshop.service.dao;
import com.mycompany.bookshop.domain.Book;
import com.mycompany.bookshop.service.util.BookHelper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDao {

    public  Long addBook(String isbn, String title,  String author, String editionYear, Double price) throws Exception {
        System.out.println("MySQL JDBC Connection Testing ~");
        Long idBook = 0L;
        String SQL_SELECT = "INSERT INTO BOOKS (ISBN, TITLE, AUTHOR, EDITIONYEAR, PRICE) VALUES (?,?,?,?, ?) ";
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/bookshop?useUnicode=true\n" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false\n" +
                "serverTimezone=UTC", "root", "");
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, author);
            preparedStatement.setBigDecimal(5, new BigDecimal(price));
            idBook = new Long(preparedStatement.executeUpdate());

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.format("Exception: %s\n%s", e.getMessage());
        }
        return  idBook;
    }

    public List<Book> getAllBooks() throws Exception {
        System.out.println("MySQL JDBC Connection Testing ~");
        List<Book> result = new ArrayList<Book>();
        String SQL_SELECT = "Select * from BOOKS";
        Class.forName("com.mysql.cj.jdbc.Driver");
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
