/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author рс
 */
class ExcelHandler {
    private void createTable(String title, Map<String, Map<Integer, Double>> map) {
        // Создайте книгу Excel
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Создайте новый лист в книге Excel
        Sheet sheet = (Sheet) workbook.createSheet();

        // Заголовок таблицы
        String[] headers = {title, "Объем ежегодной загрузки, т", "Год"};

        // Создайте новую строку в листе
        Row headerRow = sheet.createRow(0);

        // Заполните заголовок таблицы
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Данные таблицы
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

        // Сохраните книгу Excel в файл
        try (FileOutputStream outputStream = new FileOutputStream("table.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
