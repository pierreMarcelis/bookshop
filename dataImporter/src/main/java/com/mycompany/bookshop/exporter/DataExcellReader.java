package com.mycompany.bookshop.exporter;

import com.mycompany.bookshop.domain.Book;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class DataExcellReader {

// https://www.codejava.net/coding/how-to-read-excel-files-in-java-using-apache-poi

    public static List<Book> readExcelData(String fileName) throws Exception {
        List<Book> books = new ArrayList<Book>();
        ClassLoader classLoader = DataExcellReader.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        FileInputStream fis = new FileInputStream(file);

        Workbook workbook = null;

        if (fileName.toLowerCase().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(fis);
        } else if (fileName.toLowerCase().endsWith("xls")) {
            workbook = new HSSFWorkbook(fis);
        }
        Sheet bookslDataSheet = workbook.getSheet("Books");
        Row row;
        Cell cell;

        Iterator<Row> rows = bookslDataSheet.rowIterator();
        int rowIndex = 0;
        while (rows.hasNext()) {
            row = rows.next();
            if (rowIndex != 0) {
                Book book = new Book();
                for (int indexCell = 0; indexCell < row.getLastCellNum(); indexCell++) {
                    cell = row.getCell(indexCell, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    System.out.print(cell.toString() + " ");
                    String value = cell.toString();
                    switch (indexCell) {
                        default:
                            throw new Exception("Invalid souce file format");
                        case 0:
                            book.setIsbn(value);
                            break;
                        case 1:
                            book.setTitle(value);
                            break;
                        case 2:
                            book.setAuthor(value);
                            break;
                        case 3:
                            book.setEditionYear(Integer.getInteger(value));
                            break;
                        case 4:
                            book.setPrice(cell.getNumericCellValue());
                            break;
                    }
                    books.add(book);
                }
            }
            rowIndex++;
        }
            workbook.close();
            fis.close();
        return books;
    }
    }