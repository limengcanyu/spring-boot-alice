package com.veu.element.alice.admin.service.impl;

import com.veu.element.alice.admin.service.ExportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {
    private static final Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

    @Override
    public void export(HttpServletResponse response, String fileName, List<String> columnList, List<List<Object>> dataList) {
        try (
                OutputStream outputStream = response.getOutputStream()
        ) {
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            response.setContentType("application/octet-stream;charset=UTF-8");

            Workbook wb = createXlsWorkbook(columnList, dataList);

            wb.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private Workbook createXlsWorkbook(List<String> columnList, List<List<Object>> dataList) {
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet();

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
                if (cellValue instanceof Double || cellValue instanceof Integer) {
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
    public Workbook createXlsWorkbookSheet(Workbook wb, String sheetName, List<String> columnList, List<List<Object>> dataList) {
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
    public List<String> getSpecialDeductionColumnList() {
        List<String> columnList = new ArrayList<>();
        columnList.add("薪资月份");
        columnList.add("姓名");
        columnList.add("累计专项附加扣除");
        columnList.add("累计子女教育");
        columnList.add("累计住房贷款利息");
        columnList.add("累计租金");
        columnList.add("累计赡养老人");
        columnList.add("累计继续教育");
        return columnList;
    }

    @Override
    public List<List<Object>> getSpecialDeductionDataList() {
        List<List<Object>> retList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            List<Object> cellList = new ArrayList<>();
            cellList.add("2019-11");
            cellList.add("王" + i);
            cellList.add(1000 + i);
            cellList.add(1100 + i);
            cellList.add(1200 + i);
            cellList.add(1300 + i);
            cellList.add(1400 + i);
            cellList.add(1500 + i);

            retList.add(cellList);
        }

        return retList;
    }

}
