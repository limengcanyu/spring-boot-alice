package com.spring.boot.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.spring.boot.jwt.utils.Auth0JwtUtils;
import org.junit.Test;

import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/27
 */
public class Auth0JwtUtilsTest {
    @Test
    public void createHSToken() {
        String tenantId = "tenant_000001";
        String userId = "user_000001";
        String loginToken = "loginToken";
        String requestToken = "requestToken";

        String token = Auth0JwtUtils.createHSToken(tenantId, userId, loginToken, requestToken);
        System.out.println("token: " + token);

        Map<String, Claim> claimMap = Auth0JwtUtils.verifyHSToken(token);
        System.out.println("claimMap: " + claimMap);

        System.out.println("tenantId: " + claimMap.get("tenantId").asString());
        System.out.println("userId: " + claimMap.get("userId").asString());
        System.out.println("loginToken: " + claimMap.get("loginToken").asString());
        System.out.println("requestToken: " + claimMap.get("requestToken").asString());
    }

    @Test
    public void verifyHSToken() {
        String token= "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0ZW5hbnRJZCI6InRlbmFudF8wMDAwMDEiLCJsb2dpblRva2VuIjoibG9naW5Ub2tlbiIsInJlcXVlc3RUb2tlbiI6InJlcXVlc3RUb2tlbiIsInVzZXJJZCI6InVzZXJfMDAwMDAxIn0.7tnVBdKE3CJO5YXY5VO7dHS0y8CXTkh4sTktXd1qfF0";
        Map<String, Claim> claimMap = Auth0JwtUtils.verifyHSToken(token);
        System.out.println("claimMap: " + claimMap);

        System.out.println("tenantId: " + claimMap.get("tenantId").asString());
        System.out.println("userId: " + claimMap.get("userId").asString());
        System.out.println("loginToken: " + claimMap.get("loginToken").asString());
        System.out.println("requestToken: " + claimMap.get("requestToken").asString());
    }
}
