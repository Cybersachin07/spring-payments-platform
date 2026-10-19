package com.paybizz.payments;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
class JwtGeneratorTest {

    @Value("${spring.security.oauth2.resourceserver.jwt.secret-key}")
    String jwtSecret;

    @Test
    void generateToken() throws Exception {

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject("local-user")
                .issueTime(new Date())
                .expirationTime(Date.from(
                        Instant.now().plusSeconds(3600)
                ))
                .claim(
                        "scope",
                        "accounts:write accounts:read transfers:write"
                )
                .build();

        SignedJWT jwt = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claims
        );

        jwt.sign(new MACSigner(jwtSecret.getBytes()));

        System.out.println("\n================ JWT TOKEN ================\n");
        System.out.println(jwt.serialize());
        System.out.println("\n============================================\n");
    }
}