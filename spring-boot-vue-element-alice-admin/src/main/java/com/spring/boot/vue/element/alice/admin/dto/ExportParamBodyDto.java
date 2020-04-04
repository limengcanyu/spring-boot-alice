package com.spring.boot.vue.element.alice.admin.dto;

import lombok.Data;

@Data
public class ExportParamBodyDto {
    private String version;
    private String tenantId;
    private String companyId;
    private String salaryMonth;
    private int salaryBatch;
}
