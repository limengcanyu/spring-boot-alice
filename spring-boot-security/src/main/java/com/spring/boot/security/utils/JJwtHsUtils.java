package com.spring.boot.security.utils;

import io.jsonwebtoken.*;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JJwtHsUtils {
    public static final String SECRET_KEY = "LookWhatYouMadeMeDoWeAreNeverEverGettingBackTogetherEverythingHasChanged"; //秘钥

    private static SecretKey getSecretKey() {
        byte[] encodedKey = Base64Utils.decodeFromString(SECRET_KEY);
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
