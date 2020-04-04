package com.spring.boot.vue.element.alice.admin.service.impl;

import com.spring.boot.vue.element.alice.admin.service.ExportUtilsService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ExportUtilsServiceImpl implements ExportUtilsService {
    private static final Logger logger = LoggerFactory.getLogger(ExportUtilsServiceImpl.class);

    @Override
    public Workbook createXlsWorkbook() {
        return new HSSFWorkbook();
    }

    @Override
    public Workbook createXlsxWorkbook() {
        return new XSSFWorkbook();
    }

    @Override
    public Workbook createXlsSheet(Workbook wb, String sheetName, List<String> columnList, List<List<Object>> dataList) {
        Sheet sheet;
        if (StringUtils.isEmpty(sheetName)) {
            sheet = wb.createSheet();
        } else {
            sheet = wb.createSheet(sheetName);
        }

        // 写入标题列
        Row titleRow = sheet.createRow(0);

        int size = columnList.size();
        for (int i = 0; i < size; i++) {
            String columnName = columnList.get(i); // 标题

            Cell cell = titleRow.createCell(i);
            cell.setCellValue(columnName);

            // 标题顶部居中显示
            CellUtil.setVerticalAlignment(cell, VerticalAlignment.TOP);
            CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);
        }

        // 写入数据
        int rowSize = dataList.size();
        for (int i = 0; i < rowSize; i++) {
            Row dataRow = sheet.createRow(i + 1);

            List<Object> rowList = dataList.get(i); // 行数据
            int cellSize = rowList.size();
            for (int j = 0; j < cellSize; j++) {
                Object cellValue = rowList.get(j); // 列值对象

                Cell cell = dataRow.createCell(j); // 列单元格

                // 若列值是浮点数，单元格值顶层靠右显示，其它类型值顶层中间显示
                if (cellValue instanceof Double) {
                    cell.setCellValue(new BigDecimal(cellValue.toString()).setScale(2, RoundingMode.HALF_UP).toString());

                    CellUtil.setVerticalAlignment(cell, VerticalAlignment.TOP);
                    CellUtil.setAlignment(cell, HorizontalAlignment.RIGHT);
                } else {
                    cell.setCellValue(cellValue == null ? "" : cellValue.toString());

                    CellUtil.setVerticalAlignment(cell, VerticalAlignment.TOP);
                    CellUtil.setAlignment(cell, HorizontalAlignment.CENTER);
                }
            }
        }

        return wb;
    }

    @Override
    public void export(HttpServletResponse response, String fileName, Workbook wb) {
        try (
                OutputStream outputStream = response.getOutputStream()
        ) {
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            response.setContentType("application/octet-stream;charset=UTF-8");

            wb.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
