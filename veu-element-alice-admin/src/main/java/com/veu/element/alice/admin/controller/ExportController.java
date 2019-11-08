package com.veu.element.alice.admin.controller;

import com.veu.element.alice.admin.dto.ExportParamBodyDTO;
import com.veu.element.alice.admin.service.ExportDataService;
import com.veu.element.alice.admin.service.ExportUtilsService;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/export")
@RestController
public class ExportController {
    private static final Logger logger = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    private ExportUtilsService exportUtilsService;

    @Autowired
    private ExportDataService exportDataService;

    @RequestMapping("/getPathVariableExport/{version}/{tenantId}/{companyId}/{salaryMonth}")
    public void getPathVariableExport(@PathVariable("version") String version,
                                      @PathVariable("tenantId") String tenantId,
                                      @PathVariable("companyId") String companyId,
                                      @PathVariable("salaryMonth") String salaryMonth,
                                      HttpServletResponse response) {
        logger.debug("getPathVariableExport version: {} tenantId: {} companyId: {} salaryMonth: {}", version, tenantId, companyId, salaryMonth);

        List<String> columnList = exportDataService.getColumnList();
        List<List<Object>> dataList = exportDataService.getDataList();

        if (ObjectUtils.nullSafeEquals(version, "2003")) {
            String fileName = salaryMonth + "-数据.xls";
            Workbook workbook = exportUtilsService.createXlsWorkbook();
            workbook = exportUtilsService.createXlsSheet(workbook, "sheet1", columnList, dataList);
            exportUtilsService.export(response, fileName, workbook);
        } else if (ObjectUtils.nullSafeEquals(version, "2007")) {
            String fileName = salaryMonth + "-数据.xlsx";
            Workbook workbook = exportUtilsService.createXlsxWorkbook();
            workbook = exportUtilsService.createXlsSheet(workbook, "sheet1", columnList, dataList);
            exportUtilsService.export(response, fileName, workbook);
        }
    }

    @RequestMapping("/postRequestParamExport")
    public void postRequestParamExport(@RequestParam("version") String version,
                                       @RequestParam("tenantId") String tenantId,
                                       @RequestParam("companyId") String companyId,
                                       @RequestParam("salaryMonth") String salaryMonth,
                                       HttpServletResponse response) {
        logger.debug("postRequestParamExport version: {} tenantId: {} companyId: {} salaryMonth: {}", version, tenantId, companyId, salaryMonth);

        List<String> columnList = exportDataService.getColumnList();
        List<List<Object>> dataList = exportDataService.getDataList();

        if (version.equals("2003")) {
            String fileName = salaryMonth + "-数据.xls";
            Workbook workbook = exportUtilsService.createXlsWorkbook();
            workbook = exportUtilsService.createXlsSheet(workbook, "sheet1", columnList, dataList);
            exportUtilsService.export(response, fileName, workbook);
        } else if (ObjectUtils.nullSafeEquals(version, "2007")) {
            String fileName = salaryMonth + "-数据.xlsx";
            Workbook workbook = exportUtilsService.createXlsxWorkbook();
            workbook = exportUtilsService.createXlsSheet(workbook, "sheet1", columnList, dataList);
            exportUtilsService.export(response, fileName, workbook);
        }
    }

    @RequestMapping("/postRequestBodyExport")
    public void postRequestBodyExport(@RequestBody ExportParamBodyDTO exportParamBody, HttpServletResponse response) {
        String version = exportParamBody.getVersion();
        String tenantId = exportParamBody.getTenantId();
        String companyId = exportParamBody.getCompanyId();
        String salaryMonth = exportParamBody.getSalaryMonth();

        logger.debug("postRequestBodyExport version: {} tenantId: {} companyId: {} salaryMonth: {}", version, tenantId, companyId, salaryMonth);

        List<String> columnList = exportDataService.getColumnList();
        List<List<Object>> dataList = exportDataService.getDataList();

        if (version.equals("2003")) {
            String fileName = salaryMonth + "-数据.xls";
            Workbook workbook = exportUtilsService.createXlsWorkbook();
            workbook = exportUtilsService.createXlsSheet(workbook, "sheet1", columnList, dataList);
            exportUtilsService.export(response, fileName, workbook);
        } else if (ObjectUtils.nullSafeEquals(version, "2007")) {
            String fileName = salaryMonth + "-数据.xlsx";
            Workbook workbook = exportUtilsService.createXlsxWorkbook();
            workbook = exportUtilsService.createXlsSheet(workbook, "sheet1", columnList, dataList);
            exportUtilsService.export(response, fileName, workbook);
        }
    }

    @RequestMapping("/postRequestBodyExportEmptyFile")
    public void postRequestBodyExportEmptyDataFile(@RequestBody ExportParamBodyDTO exportParamBody, HttpServletResponse response) {
        String version = exportParamBody.getVersion();
        String tenantId = exportParamBody.getTenantId();
        String companyId = exportParamBody.getCompanyId();
        String salaryMonth = exportParamBody.getSalaryMonth();

        logger.debug("postRequestBodyExport version: {} tenantId: {} companyId: {} salaryMonth: {}", version, tenantId, companyId, salaryMonth);

        List<String> columnList = exportDataService.getEmptyColumnList();
        List<List<Object>> dataList = exportDataService.getEmptyDataList();

        if (version.equals("2003")) {
            String fileName = salaryMonth + "-数据.xls";
            Workbook workbook = exportUtilsService.createXlsWorkbook();
            workbook = exportUtilsService.createXlsSheet(workbook, "sheet1", columnList, dataList);
            exportUtilsService.export(response, fileName, workbook);
        } else if (ObjectUtils.nullSafeEquals(version, "2007")) {
            String fileName = salaryMonth + "-数据.xlsx";
            Workbook workbook = exportUtilsService.createXlsxWorkbook();
            workbook = exportUtilsService.createXlsSheet(workbook, "sheet1", columnList, dataList);
            exportUtilsService.export(response, fileName, workbook);
        }
    }
}
