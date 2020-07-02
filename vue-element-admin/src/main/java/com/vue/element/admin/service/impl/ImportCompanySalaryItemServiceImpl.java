package com.vue.element.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.vue.element.admin.mybatisplus.entity.CompanySalaryItem;
import com.vue.element.admin.mybatisplus.service.ICompanySalaryItemService;
import com.vue.element.admin.service.ImportCompanySalaryItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author rock
 * time 2020/7/2 0002 9:48
 */
@Slf4j
@Service
public class ImportCompanySalaryItemServiceImpl implements ImportCompanySalaryItemService {

    @Autowired
    private ICompanySalaryItemService companySalaryItemService;

    @Override
    public void importCompanySalaryItem(String fileName) {
        try {
            Workbook wb = WorkbookFactory.create(new File(fileName));

            Sheet salaryItemDefinitionSheet = wb.getSheet("工资结构详细定义");

            List<CompanySalaryItem> itemDefinitionList = processSalaryItemDefinitionSheet(salaryItemDefinitionSheet);
            boolean saveBatchResult = companySalaryItemService.saveBatch(itemDefinitionList);

            System.out.println("保存公司薪资项 " + (saveBatchResult ? "成功！" : "失败！"));

            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.debug("读取薪资项定义文件失败！");
        }
    }

    private List<CompanySalaryItem> processSalaryItemDefinitionSheet(Sheet salaryItemDefinitionSheet) {
        // 标题List
        List<String> titleList = getSalaryItemDefinitionTitleList(salaryItemDefinitionSheet);

        int firstRowNum = salaryItemDefinitionSheet.getFirstRowNum();
        int lastRowNum = salaryItemDefinitionSheet.getLastRowNum();

        List<CompanySalaryItem> itemDefinitionList = new ArrayList<>();
        for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
            Row dataRow = salaryItemDefinitionSheet.getRow(i);
            CompanySalaryItem itemDefinition = processSalaryItemDefinitionRow(titleList, dataRow);
            itemDefinitionList.add(itemDefinition);
        }

        System.out.println("薪资项定义List: " + JSONObject.toJSONString(itemDefinitionList));
        return itemDefinitionList;

    }

    /**
     * 获取标题List
     *
     * @param salaryItemDefinitionSheet
     * @return
     */
    private List<String> getSalaryItemDefinitionTitleList(Sheet salaryItemDefinitionSheet) {
        List<String> titleList = new ArrayList<>();

        Row titleRow = salaryItemDefinitionSheet.getRow(0);

        short firstCellNum = titleRow.getFirstCellNum();
        short lastCellNum = titleRow.getLastCellNum();

        for (int i = firstCellNum; i < lastCellNum; i++) {
            titleList.add(titleRow.getCell(i).getStringCellValue());
        }

        System.out.println("titleList: " + JSONObject.toJSONString(titleList));
        return titleList;
    }

    private CompanySalaryItem processSalaryItemDefinitionRow(List<String> titleList, Row dataRow) {
        short firstCellNum = dataRow.getFirstCellNum();
        short lastCellNum = dataRow.getLastCellNum();

        CompanySalaryItem itemDefinition = new CompanySalaryItem();

        for (int i = firstCellNum; i < lastCellNum; i++) {
            String title = titleList.get(i); // 当前列标题

            Cell cell = dataRow.getCell(i);

            if (cell != null ) {
                switch (title) {
                    case "显示顺序":
                        itemDefinition.setDisplayOrder(Double.valueOf(cell.getNumericCellValue()).intValue());
                        break;
                    case "计算顺序":
                        itemDefinition.setComputeOrder(Double.valueOf(cell.getNumericCellValue()).intValue());
                        break;

                    // 病事假扣款基数项类型：1-必选项；2-可选项
                    case "病事假扣款基数项":
                        String float_salary_base_itemString = cell.getStringCellValue();
                        switch (float_salary_base_itemString) {
                            case "必选项":
                                itemDefinition.setSickLeaveDeductBaseItem(1); // 浮动工资基数项：1-是；0-否
                                break;
                            case "可选项":
                                itemDefinition.setSickLeaveDeductBaseItem(2);
                                break;
                            default:
                                break;
                        }
                        break;
                    // 加班费基数项类型：1-必选项；2-可选项
                    case "加班费基数项":
                        String overtime_base_itemString = cell.getStringCellValue();
                        switch (overtime_base_itemString) {
                            case "必选项":
                                itemDefinition.setOvertimeSalaryBaseItem(1); // 加班费基数项：1-是；0-否
                                break;
                            case "可选项":
                                itemDefinition.setOvertimeSalaryBaseItem(2);
                                break;
                            default:
                                break;
                        }
                        break;
                    // 工资调整基数项类型：1-必选项；2-可选项
                    case "工资调整基数项":
                        String fixed_allowance_itemString = cell.getStringCellValue();
                        switch (fixed_allowance_itemString) {
                            case "必选项":
                                itemDefinition.setAdjustSalaryBaseItem(1); // 固定津贴项：1-是；0-否
                                break;
                            case "可选项":
                                itemDefinition.setAdjustSalaryBaseItem(2);
                                break;
                            default:
                                break;
                        }
                        break;

                    case "薪资项编码":
                        itemDefinition.setItemCode(cell.getStringCellValue());
                        break;
                    case "薪资项名称":
                        itemDefinition.setItemName(cell.getStringCellValue());
                        break;
                    case "薪资项变量":
                        itemDefinition.setItemVariant(cell.getStringCellValue());
                        break;
                    case "薪资项别名":
                        itemDefinition.setItemAlias(cell.getStringCellValue());
                        break;
                    // 薪资项类型：1-文本项；2-输入项；3-计算项；4-引用项
                    case "薪资项类型":
                        String itemTypeString = cell.getStringCellValue();
                        switch (itemTypeString) {
                            case "文本项":
                                itemDefinition.setItemType(1);
                                break;
                            case "输入项":
                                itemDefinition.setItemType(2);
                                break;
                            case "计算项":
                                itemDefinition.setItemType(3);
                                break;
                            default:
                                break;
                        }
                        break;
                    // 数据类型：1-文本；2-整数；3-小数
                    case "数据类型":
                        String dataTypeString = cell.getStringCellValue();
                        switch (dataTypeString) {
                            case "文本":
                                itemDefinition.setDataType(1);
                                break;
                            case "整数":
                                itemDefinition.setDataType(2);
                                break;
                            case "小数":
                                itemDefinition.setDataType(3);
                                break;
                            default:
                                break;
                        }
                        break;
                    case "数据长度":
                        itemDefinition.setDataLength(Double.valueOf(cell.getNumericCellValue()).intValue());
                        break;
                    case "小数位数":
                        itemDefinition.setDecimalDigits(Double.valueOf(cell.getNumericCellValue()).intValue());
                        break;
                    case "开发用计算公式":
                        itemDefinition.setComputeExpression(cell.getStringCellValue());
                        break;
                    default:
                        break;
                }
            }
        }

        return itemDefinition;
    }
}
