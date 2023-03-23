package helpers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import commons.GlobalConstants;
import org.apache.poi.ss.usermodel.*;

public class ExcelHelpers {
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private final Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String excelPath, String sheetName) {
        try {
            fileInputStream = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fileInputStream);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getCellData(int rowNum, int colNum, String excelPath, String sheetName) {
        try {
            setExcelFile(excelPath, sheetName);
            cell = sheet.getRow(rowNum).getCell(colNum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    public String getCellData(String columnName, int rowNum, String excelPath, String sheetName) {
        try {
            setExcelFile(excelPath, sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return getCellData(rowNum, columns.get(columnName), excelPath, sheetName);
    }

    public void setCellData(String text, int rowNum, int colNum, String excelPath, String sheetName) {
        try {
            setExcelFile(excelPath, sheetName);
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
            }
            cell.setCellValue(text);

            fileOutputStream = new FileOutputStream(excelPath);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        System.out.println(excelHelpers.getCellData("Age", 1, GlobalConstants.CONFIG_PROPERTY_PATH, GlobalConstants.SHEET.LOGIN.toString()));


    }

}
