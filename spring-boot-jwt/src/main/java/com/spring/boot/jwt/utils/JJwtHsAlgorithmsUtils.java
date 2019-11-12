package com.spring.boot.jwt.utils;

import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JJwtHsAlgorithmsUtils {
    private static SecretKey getSecretKey() {
        byte[] encodedKey = Base64.decodeBase64(JwtConstant.JWT_SECRET);
        return new SecretKeySpec(encodedKey, "HmacSHA256");
    }

    public static String creatJWS(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims readJWS(String jwsString) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .parseClaimsJws(jwsString);
            return jws.getBody();
        } catch (JwtException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
