package com.veu.element.alice.admin.service;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExportService {
    void export(HttpServletResponse response, String fileName, List<String> columnList, List<List<Object>> dataList);
    void export(HttpServletResponse response, String fileName, Workbook wb);

    Workbook createXlsWorkbookSheet(Workbook wb, String sheetName, List<String> columnList, List<List<Object>> dataList);

    List<String> getSpecialDeductionColumnList();
    List<List<Object>> getSpecialDeductionDataList();

}
