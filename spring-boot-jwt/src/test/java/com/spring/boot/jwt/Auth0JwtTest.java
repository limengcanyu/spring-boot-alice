package com.spring.boot.jwt;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: Need Create a free Auth0 Account </p>
 *
 * @author rock
 * date 2019/06/23
 */
public class Auth0JwtTest {
    @Test
    public void HS256() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    @Test
    public void RS256() {
        try {
            JwkProvider provider = new JwkProviderBuilder("https://samples.auth0.com/")
                    .cached(10, 24, TimeUnit.HOURS)
                    .rateLimited(10, 1, TimeUnit.MINUTES)
                    .build();
            Jwk jwk = provider.get("{kid of the signing key}"); //throws Exception when not found or can't get one
            System.out.println("jwk: " + jwk);
        } catch (JwkException e) {
            e.printStackTrace();
        }

//        RSAPublicKey publicKey = //Get the key instance
//        RSAPrivateKey privateKey = //Get the key instance
//
//        try {
//            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
//            String token = JWT.create()
//                    .withIssuer("auth0")
//                    .sign(algorithm);
//        } catch (JWTCreationException exception){
//            //Invalid Signing configuration / Couldn't convert Claims.
//        }
    }
}
