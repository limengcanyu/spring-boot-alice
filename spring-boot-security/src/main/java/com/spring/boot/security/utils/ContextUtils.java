package com.spring.boot.security.utils;

import com.spring.boot.security.entity.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

/**
 * <p>Description: Context Utils</p>
 *
 * @author rock.jiang
 * Date 2019/11/28 17:16
 */
@Slf4j
public class ContextUtils {
    private static final ThreadLocal<JwtUser> userContext = new ThreadLocal<>();

    public static void setUser(JwtUser user) {
        userContext.remove();
        userContext.set(user);
    }

    public static String getTenantId() {
        if (!ObjectUtils.isEmpty(userContext.get())) {
            return userContext.get().getTenantId();
        }
        return "tenant_000000";
    }

    public static String getCompanyId() {
        if (!ObjectUtils.isEmpty(userContext.get())) {
            return userContext.get().getCompanyId();
        }
        return "company_000000";
    }

    public static String getUserId() {
        if (!ObjectUtils.isEmpty(userContext.get())) {
            return userContext.get().getUserId();
        }
        return "user_000000";
    }

    public static String getUsername() {
        if (!ObjectUtils.isEmpty(userContext.get())) {
            return userContext.get().getUsername();
        }
        return "user_000000";
    }
}
