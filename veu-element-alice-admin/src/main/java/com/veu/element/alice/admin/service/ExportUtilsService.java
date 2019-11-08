package com.veu.element.alice.admin.service;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExportUtilsService {
    Workbook createXlsWorkbook();
    Workbook createXlsxWorkbook();
    Workbook createXlsSheet(Workbook wb, String sheetName, List<String> columnList, List<List<Object>> dataList);
    void export(HttpServletResponse response, String fileName, Workbook wb);
}
