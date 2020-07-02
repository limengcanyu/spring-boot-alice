package com.vue.element.admin.utils;

import com.vue.element.admin.dto.CompanyUserDto;
import com.vue.element.admin.mybatisplus.entity.CompanyUser;
import org.springframework.util.ObjectUtils;

/**
 * description:
 *
 * @author rock
 * time 2020/7/2 0002 9:15
 */
public class ContextUtils {
    private static final ThreadLocal<CompanyUserDto> userContext = new ThreadLocal<>();

    public static void setUser(CompanyUserDto companyUserDto) {
        userContext.remove();
        userContext.set(companyUserDto);
    }

    public static void setTenantId(String tenantId) {
        CompanyUserDto companyUser = new CompanyUserDto();
        companyUser.setTenantId(tenantId);
        userContext.set(companyUser);
    }

    public static String getTenantId() {
        if (ObjectUtils.isEmpty(userContext.get())) {
            return "tenant_000000";
        } else {
            return userContext.get().getTenantId();
        }
    }

    public static String getCompanyId() {
        if (ObjectUtils.isEmpty(userContext.get())) {
            return "company_000000";
        } else {
            return userContext.get().getCompanyId();
        }
    }

    public static String getUserId() {
        if (ObjectUtils.isEmpty(userContext.get())) {
            return "user_000000";
        } else {
            return userContext.get().getUserId();
        }
    }
}
