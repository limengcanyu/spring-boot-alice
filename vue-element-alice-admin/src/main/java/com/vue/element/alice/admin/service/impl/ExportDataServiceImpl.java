package com.vue.element.alice.admin.service.impl;

import com.vue.element.alice.admin.service.ExportDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExportDataServiceImpl implements ExportDataService {
    private static final Logger logger = LoggerFactory.getLogger(ExportDataServiceImpl.class);

    @Override
    public List<String> getColumnList() {
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
    public List<List<Object>> getDataList() {
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

    @Override
    public List<String> getEmptyColumnList() {
        return new ArrayList<>();
    }

    @Override
    public List<List<Object>> getEmptyDataList() {
        return new ArrayList<>();
    }
}
