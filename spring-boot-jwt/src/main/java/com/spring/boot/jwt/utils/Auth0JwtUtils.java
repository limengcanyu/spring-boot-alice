package com.spring.boot.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

/**
 * <p>Description: Auth0 Jwt Utils </p>
 *
 * @author rock
 * date 2019/06/27
 */
public class Auth0JwtUtils {
    private static final Algorithm algorithm = Algorithm.HMAC256("secret");

    public static String createHSToken(String tenantId, String userId, String loginToken, String requestToken){
        try {
            String token = JWT.create()
                    .withClaim("tenantId", tenantId)
                    .withClaim("userId", userId)
                    .withClaim("loginToken", loginToken)
                    .withClaim("requestToken", requestToken)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            System.out.println("生成token错误！");
        }

        return null;
    }

    public static Map<String, Claim> verifyHSToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            System.out.println("解析token错误！");
        }

        return null;
    }

    public static String createRSToken(String tenantId, String userId, String loginToken, String requestToken){

        return null;
    }

    public static Map<String, Claim> verifyRSToken(String token){
        return null;
    }
}
