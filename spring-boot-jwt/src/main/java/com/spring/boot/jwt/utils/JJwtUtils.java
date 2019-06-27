package com.spring.boot.jwt.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


/**
 * <p>Description: JJwt Utils </p>
 *
 * @author rock
 * date 2019/06/27
 */
public class JJwtUtils {
    //    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512 出现 SignatureException
//    private static final SecretKey key = Keys.hmacShaKeyFor("FDFSDFSDGSDEFDSFWEFEFSFDSFSDFDSFFSDFSDFSDF".getBytes()); //or HS384 or HS512 正常
    private static final SecretKey key = new SecretKeySpec("sadfsadfdsafasdfdsafasdfasdfasdfadsfasdfasdf".getBytes(), SignatureAlgorithm.HS256.getJcaName());

//    private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256); //or RS384, RS512, PS256, PS384, PS512, ES256, ES384, ES512

    /**
     * create HS Token
     *
     * @param tenantId
     * @param userId
     * @param loginToken
     * @param requestToken
     * @return
     */
    public static String createHSToken(String tenantId, String userId, String loginToken, String requestToken) {
        Claims claims = Jwts.claims();
        claims.put("tenantId", tenantId);
        claims.put("userId", userId);
        claims.put("loginToken", loginToken);
        claims.put("requestToken", requestToken);

        String jws = Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return jws;
    }

    /**
     * parse HS Token
     *
     * @param token
     * @return
     */
    public static Claims parseHSToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            // we can safely trust the JWT
            return claimsJws.getBody();
        } catch (JwtException ex) {
            // we *cannot* use the JWT as intended by its creator
            System.out.println("解析token错误！");
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * create RS Token
     *
     * @param tenantId
     * @param userId
     * @param loginToken
     * @param requestToken
     * @return
     */
    public static String createRSToken(String tenantId, String userId, String loginToken, String requestToken) {
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256); //or RS384, RS512, PS256, PS384, PS512, ES256, ES384, ES512

        PrivateKey privateKey = keyPair.getPrivate();

        Claims claims = Jwts.claims();
        claims.put("tenantId", tenantId);
        claims.put("userId", userId);
        claims.put("loginToken", loginToken);
        claims.put("requestToken", requestToken);

        String jws = Jwts.builder()
                .setClaims(claims)
                .signWith(privateKey, SignatureAlgorithm.RS512) // <---
                .compact();

        return jws;
    }

    /**
     * parse RS Token
     *
     * @param token
     * @return
     */
    public static Claims parseRSToken(String token) {
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256); //or RS384, RS512, PS256, PS384, PS512, ES256, ES384, ES512

        PublicKey publicKey = keyPair.getPublic();

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(publicKey) // <---- publicKey, not privateKey
                    .parseClaimsJws(token);
            // we can safely trust the JWT
            return claimsJws.getBody();
        } catch (JwtException ex) {
            // we *cannot* use the JWT as intended by its creator
            System.out.println("解析token错误！");
            ex.printStackTrace();
        }

        return null;
    }

}
