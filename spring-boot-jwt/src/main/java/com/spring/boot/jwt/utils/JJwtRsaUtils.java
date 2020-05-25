package com.spring.boot.jwt.utils;

import com.spring.boot.jwt.constant.JwtConst;
import com.spring.boot.jwt.constant.TokenConst;
import com.spring.boot.jwt.entity.TokenEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.RsaProvider;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class JJwtRsaUtils {
    private static PublicKey getPublicKey() {
        int keySizeInBits = 4096;
        SecureRandom random = new SecureRandom(JwtConst.SECRET_KEY.getBytes());
        KeyPair keyPair = RsaProvider.generateKeyPair(keySizeInBits, random);
        return keyPair.getPublic();
    }

    private static PrivateKey getPrivateKey() {
        int keySizeInBits = 4096;
        SecureRandom random = new SecureRandom(JwtConst.SECRET_KEY.getBytes());
        KeyPair keyPair = RsaProvider.generateKeyPair(keySizeInBits, random);
        return keyPair.getPrivate();
    }

    public static String creatJWS(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(getPrivateKey())
                .compact();
    }

    public static String creatJWS(TokenEntity tokenEntity) {
        Claims claims = Jwts.claims();

        claims.put(TokenConst.TENANT_ID, tokenEntity.getTenantId());
        claims.put(TokenConst.COMPANY_ID, tokenEntity.getCompanyId());
        claims.put(TokenConst.USER_ID, tokenEntity.getUserId());
        claims.put(TokenConst.LOGIN_UUID, tokenEntity.getLoginUUID());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(getPrivateKey())
                .compact();
    }

    public static Claims readJWS(String jwsString) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(getPublicKey())
                    .parseClaimsJws(jwsString);
            return jws.getBody();
        } catch (JwtException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
