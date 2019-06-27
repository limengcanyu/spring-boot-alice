package com.spring.boot.jwt;

import com.spring.boot.jwt.utils.JJwtUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * <p>Description: </p>
 *
 * @author rock
 * date 2019/06/27
 */
public class JJwtUtilsTest {
    @Test
    public void createHSToken() {
        String tenantId = "tenant_000001";
        String userId = "user_000001";
        String loginToken = "loginToken";
        String requestToken = "requestToken";

        String token = JJwtUtils.createHSToken(tenantId, userId, loginToken, requestToken);
        System.out.println("token: " + token);

        Claims claims = JJwtUtils.parseHSToken(token);
        System.out.println("claims: " + claims);

        System.out.println("tenantId: " + claims.get("tenantId"));
        System.out.println("userId: " + claims.get("userId"));
        System.out.println("loginToken: " + claims.get("loginToken"));
        System.out.println("requestToken: " + claims.get("requestToken"));
    }

    @Test
    public void parseHSToken() {
        String token= "eyJhbGciOiJIUzI1NiJ9.eyJ0ZW5hbnRJZCI6InRlbmFudF8wMDAwMDEiLCJ1c2VySWQiOiJ1c2VyXzAwMDAwMSIsImxvZ2luVG9rZW4iOiJsb2dpblRva2VuIiwicmVxdWVzdFRva2VuIjoicmVxdWVzdFRva2VuIn0.22JYX28E7xCK3hYxdHpc0JRyjiBCD9r4YoVqWShui6A";

        Claims claims = JJwtUtils.parseHSToken(token);
        System.out.println("claims: " + claims);

        System.out.println("tenantId: " + claims.get("tenantId"));
        System.out.println("userId: " + claims.get("userId"));
        System.out.println("loginToken: " + claims.get("loginToken"));
        System.out.println("requestToken: " + claims.get("requestToken"));
    }

    @Test
    public void createRSToken() {
        String tenantId = "tenant_000001";
        String userId = "user_000001";
        String loginToken = "loginToken";
        String requestToken = "requestToken";

        String token = JJwtUtils.createRSToken(tenantId, userId, loginToken, requestToken);
        System.out.println("token: " + token);

        Claims claims = JJwtUtils.parseRSToken(token);
        System.out.println("claims: " + claims);

        System.out.println("tenantId: " + claims.get("tenantId"));
        System.out.println("userId: " + claims.get("userId"));
        System.out.println("loginToken: " + claims.get("loginToken"));
        System.out.println("requestToken: " + claims.get("requestToken"));
    }

    @Test
    public void parseRSToken() {
        String token= "eyJhbGciOiJSUzUxMiJ9.eyJ0ZW5hbnRJZCI6InRlbmFudF8wMDAwMDEiLCJ1c2VySWQiOiJ1c2VyXzAwMDAwMSIsImxvZ2luVG9rZW4iOiJsb2dpblRva2VuIiwicmVxdWVzdFRva2VuIjoicmVxdWVzdFRva2VuIn0.NADUoO6XEjScSJ1nwp5mHCkRs1lzOcVRSAofhG-SqoyDK5_Lmj5RtJYafLvHTGxgBF3QujXXqi-9eieZB9K8KYtmf2HYyxlVoZ8kigOmumTVIPCrGyQEwYRjFwmbgdy_Xd2czScU_c0wpT2iVABOnkjAEjYzl4KVAYvwEMnR-KF4DRmycbSrDGAzi0Mf1fXLl98XloxV1AshNyUghSoddXcqv_ArcsNncv_jNCVM-XVoEqw2yUM7-a3rVgd7Mp3ii5Qsalw5D2JLRmOlvl-NZMx0169Cg9nfrxgYPoDEML72OfgF3ZWfeo8Q7nVTQaGN0LSCVUa_HWdun-O6BUNl5A";

        Claims claims = JJwtUtils.parseRSToken(token);
        System.out.println("claims: " + claims);

        System.out.println("tenantId: " + claims.get("tenantId"));
        System.out.println("userId: " + claims.get("userId"));
        System.out.println("loginToken: " + claims.get("loginToken"));
        System.out.println("requestToken: " + claims.get("requestToken"));
    }

    @Test
    public void createHS256Token() {
        String tenantId = "tenant_000001";
        String userId = "user_000001";
        String loginToken = "loginToken";
        String requestToken = "requestToken";

        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512 出现 SignatureException
//        SecretKey key = Keys.hmacShaKeyFor("FDFSDFSDGSDEFDSFWEFEFSFDSFSDFDSFFSDFSDFSDF".getBytes()); //or HS384 or HS512 正常

        Claims claims = Jwts.claims();
        claims.put("tenantId", tenantId);
        claims.put("userId", userId);
        claims.put("loginToken", loginToken);
        claims.put("requestToken", requestToken);

        String jws = Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("token: " + jws);

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jws);
            // we can safely trust the JWT
            System.out.println("claimsJws: " + claimsJws);
        } catch (JwtException ex) {
            // we *cannot* use the JWT as intended by its creator
            System.out.println("解析token错误！");
            ex.printStackTrace();
        }
    }

    @Test
    public void parseHS256Token() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ0ZW5hbnRJZCI6InRlbmFudF8wMDAwMDEiLCJ1c2VySWQiOiJ1c2VyXzAwMDAwMSIsImxvZ2luVG9rZW4iOiJsb2dpblRva2VuIiwicmVxdWVzdFRva2VuIjoicmVxdWVzdFRva2VuIn0.i-SgqjdOEDucTIeYpOhtSU8izw1CrziFeALRGO4wiUE";

        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512 出现 SignatureException
//        SecretKey key = Keys.hmacShaKeyFor("FDFSDFSDGSDEFDSFWEFEFSFDSFSDFDSFFSDFSDFSDF".getBytes()); //or HS384 or HS512 正常

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            // we can safely trust the JWT
            System.out.println("claimsJws: " + claimsJws);
        } catch (JwtException ex) {
            // we *cannot* use the JWT as intended by its creator
            System.out.println("解析token错误！");
            ex.printStackTrace();
        }
    }

    @Test
    public void createJWT() {
        String tenantId = "tenant_000001";
        String userId = "user_000001";
        String loginToken = "loginToken";
        String requestToken = "requestToken";

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary();
        Key signingKey = new SecretKeySpec("sadfsadfdsafasdfdsafasdfasdfasdfadsfasdfasdf".getBytes(), signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims();
        claims.put("tenantId", tenantId);
        claims.put("userId", userId);
        claims.put("loginToken", loginToken);
        claims.put("requestToken", requestToken);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId("sdfsdfsad")
                .setClaims(claims)
                .signWith(signingKey, signatureAlgorithm);

        //Builds the JWT and serializes it to a compact, URL-safe string
        String jwt = builder.compact();
        System.out.println(jwt);


        //This line will throw an exception if it is not a signed JWS (as expected)
        claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(jwt).getBody();

        System.out.println(claims);
    }

    @Test
    public void parseJWT() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJ0ZW5hbnRJZCI6InRlbmFudF8wMDAwMDEiLCJ1c2VySWQiOiJ1c2VyXzAwMDAwMSIsImxvZ2luVG9rZW4iOiJsb2dpblRva2VuIiwicmVxdWVzdFRva2VuIjoicmVxdWVzdFRva2VuIn0.22JYX28E7xCK3hYxdHpc0JRyjiBCD9r4YoVqWShui6A";

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Key signingKey = new SecretKeySpec("sadfsadfdsafasdfdsafasdfasdfasdfadsfasdfasdf".getBytes(), signatureAlgorithm.getJcaName());

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(jwt).getBody();

        System.out.println(claims);
    }

}
