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
                    System.out.print(getCellValue(cell) + " \n");
                    switch (indexCell) {
                        default:
                            throw new Exception("Invalid souce file format");
                        case 0:
                            book.setIsbn(getCellValue(cell));
                            break;
                        case 1:
                            book.setTitle(getCellValue(cell));
                            break;
                        case 2:
                            book.setAuthor(getCellValue(cell));
                            break;
                        case 3:
                            book.setEditionYear(getCellValue(cell));
                            break;
                        case 4:
                            book.setPrice(cell.getNumericCellValue());
                            break;
                    }
                }
                books.add(book);
            }
            rowIndex++;
        }
            workbook.close();
            fis.close();
        return books;
    }


    public static boolean isCellEmpty(final Cell cell) {
        if (cell == null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
            return true;
        }

        if (cell.getCellType() == CellType.BLANK) {
            return true;
        }

        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    private  static  String getCellValue(Cell cell) {
        if(isCellEmpty(cell)) return "";
        switch (cell.getCellType()) {
            default: return "";
            case BLANK:  return  "";
            case STRING: return cell.getRichStringCellValue().getString();
            case NUMERIC: return  "" + cell.getNumericCellValue();
            case BOOLEAN:  return  Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:  return  cell.getCellFormula();
        }
    }
    }