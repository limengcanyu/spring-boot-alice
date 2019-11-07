package com.veu.element.alice.admin.controller;

import com.veu.element.alice.admin.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/export")
@RestController
public class ExportController {
    private static final Logger logger = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    private ExportService exportService;

    @RequestMapping("/getExportSpecialDeduction/{version}/{tenantId}/{companyId}/{salaryMonth}")
//    @RequestMapping(value = "/getExportSpecialDeduction/{version}/{tenantId}/{companyId}/{salaryMonth}", produces = "application/octet-stream;charset=UTF-8")
    public void getExportSpecialDeduction(@PathVariable("version") String version,
                                          @PathVariable("tenantId") String tenantId, @PathVariable("companyId") String companyId,
                                          @PathVariable("salaryMonth") String salaryMonth, HttpServletResponse response) {
        logger.debug("导出专项扣除 version: {} tenantId: {} companyId: {} salaryMonth: {}", version, tenantId, companyId, salaryMonth);

        // 生成Excel导出数据
        List<String> columnList = exportService.getSpecialDeductionColumnList();
        List<List<Object>> dataList = exportService.getSpecialDeductionDataList();

        String fileName = salaryMonth + "-专项附加扣除数据.xls";

        if (version.equals("2003")) {
            exportService.export(response, fileName, columnList, dataList);
        }
    }

    @RequestMapping("/postExportSpecialDeduction")
    public void postExportSpecialDeduction(@RequestParam("version") String version,
                                           @RequestParam("tenantId") String tenantId, @RequestParam("companyId") String companyId,
                                           @RequestParam("salaryMonth") String salaryMonth, HttpServletResponse response) {
        logger.debug("导出专项扣除 version: {} tenantId: {} companyId: {} salaryMonth: {}", version, tenantId, companyId, salaryMonth);

        // 生成Excel导出数据
        List<String> columnList = exportService.getSpecialDeductionColumnList();
        List<List<Object>> dataList = exportService.getSpecialDeductionDataList();

        String fileName = salaryMonth + "-专项附加扣除数据.xls";

        if (version.equals("2003")) {
            exportService.export(response, fileName, columnList, dataList);
        }
    }

}
