package com.vue.element.alice.admin.service;

import java.util.List;

public interface ExportDataService {
    List<String> getColumnList();
    List<List<Object>> getDataList();

    List<String> getEmptyColumnList();
    List<List<Object>> getEmptyDataList();
}
