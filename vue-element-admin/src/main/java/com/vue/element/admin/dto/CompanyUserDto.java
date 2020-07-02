package com.vue.element.admin.dto;

import lombok.Data;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/23
 */
@Data
public class CompanyUserDto {
    private String username;
    private String password;

    private String userId;
    private String tenantId;
    private String companyId;
    private String employeeId;
    private String email;
    /**
     * 是否启用：1-启用；0-禁用
     */
    private Integer activeFlag;
    private Integer version;

    private String name;
    private String avatar;
    private String introduction;
    private List<String> roles;
    private List<String> routePaths;
    private List<String> routeNames;

    public CompanyUserDto() {
    }

    public CompanyUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
