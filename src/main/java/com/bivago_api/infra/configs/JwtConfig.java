package com.bivago_api.infra.configs;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtConfig {
    
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    public static final long EXPIRATION_TIME_MS = 36000000L * 24;
    
    private String secretKey = "SuperHyperSecretKeyWith32BytesHere!";

    public String getSecretKey() { return secretKey; }

}
