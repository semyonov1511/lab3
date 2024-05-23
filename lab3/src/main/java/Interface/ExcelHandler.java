/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


class ExcelHandler {
    public void createTable(String title, Map<String, Map<Integer, Double>> map) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = (Sheet) workbook.createSheet();
        String[] headers = {title, "Объем ежегодной загрузки, т", "Год"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        if (map != null) {
            int rowIndex = 1;
            for (Map.Entry<String, Map<Integer, Double>> entry : map.entrySet()) {
                Map<Integer, Double> fuelLoad = entry.getValue();
                for (Map.Entry<Integer, Double> load : fuelLoad.entrySet()) {
                    Row row = sheet.createRow(rowIndex);
                    Cell cell1 = row.createCell(0);
                    cell1.setCellValue(entry.getKey());
                    Cell cell2 = row.createCell(1);
                    cell2.setCellValue(Math.round(load.getValue()));
                    Cell cell3 = row.createCell(2);
                    cell3.setCellValue(load.getKey());
                    rowIndex++;
                }
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream("table.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
