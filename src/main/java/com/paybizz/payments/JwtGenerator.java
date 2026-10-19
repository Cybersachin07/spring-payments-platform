package com.paybizz.payments;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;

public class JwtGenerator {

    public static void main(String[] args) throws Exception {

        String secret = "01234567890123456789012345678901";

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject("local-user")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + 3600000))
                .claim("scope", "accounts:write")
                .build();

        SignedJWT jwt = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claims
        );

        jwt.sign(new MACSigner(secret.getBytes()));

        System.out.println(jwt.serialize());
    }
}