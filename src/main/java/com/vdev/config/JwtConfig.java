
package com.vdev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.KeyUse;

@Configuration
public class JwtConfig {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecretKey jwtSecretKey() {
        // TODO 1: Decode the Base64 secret into bytes.
        byte[] decodedSecret = Base64.getDecoder().decode(jwtSecret);
        // TODO 2: Create and return a SecretKey using HmacSHA256.
        return new SecretKeySpec(decodedSecret, "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder(SecretKey jwtSecretKey) {
        // TODO 3: Create a NimbusJwtEncoder using the secret key.
        OctetSequenceKey jwk = new OctetSequenceKey.Builder(jwtSecretKey)
                .keyID("store-management-key")
                .algorithm(JWSAlgorithm.HS256)
                .keyUse(KeyUse.SIGNATURE)
                .build();

        ImmutableJWKSet<SecurityContext> jwkSource =
                new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder(SecretKey jwtSecretKey) {
        // TODO 4: Create a NimbusJwtDecoder using the secret key.
        return NimbusJwtDecoder.withSecretKey(jwtSecretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}
