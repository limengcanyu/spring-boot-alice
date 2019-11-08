package com.veu.element.alice.admin.dto;

import lombok.Data;

@Data
public class ExportParamBodyDTO {
    private String version;
    private String tenantId;
    private String companyId;
    private String salaryMonth;
    private int salaryBatch;
}
