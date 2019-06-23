package com.spring.boot.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;

import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * <p>Description: JJwt Test</p>
 *
 * @author rock
 * date 2019/06/23
 */
public class JJwtTest {

    @Test
    public void HS256() {
        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jws = Jwts.builder()
                .setSubject("Bob")
                .signWith(key)
                .compact();
        System.out.println("jws: " + jws);

        try {

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jws);
            System.out.println("claimsJws: " + claimsJws);

            //OK, we can trust this JWT

        } catch (JwtException e) {

            //don't trust the JWT!
        }
    }

    @Test
    public void RS256() {
        // Signed JWTs
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256); //or RS384, RS512, PS256, PS384, PS512, ES256, ES384, ES512
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        Claims claims = new DefaultClaims();
        claims.put("username", "rock");
        claims.put("password", "1234567890");

        String jws = Jwts.builder()
                .setSubject("Bob")
                .setClaims(claims)
                .signWith(privateKey)
                .compact();
        System.out.println("jws: " + jws);

        // Reading a JWS
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(publicKey) // <---- publicKey, not privateKey
                    .parseClaimsJws(jws);
            System.out.println("claimsJws: " + claimsJws);

            // we can safely trust the JWT

        } catch (JwtException ex) {

            // we *cannot* use the JWT as intended by its creator
        }
    }
}
