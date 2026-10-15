warning: in the working copy of 'src/main/resources/application.properties', LF will be replaced by CRLF the next time Git touches it
[1mdiff --git a/src/main/java/com/vdev/config/JwtConfig.java b/src/main/java/com/vdev/config/JwtConfig.java[m
[1mindex 2e18567..f0deef9 100644[m
[1m--- a/src/main/java/com/vdev/config/JwtConfig.java[m
[1m+++ b/src/main/java/com/vdev/config/JwtConfig.java[m
[36m@@ -1,4 +1,60 @@[m
[32m+[m
 package com.vdev.config;[m
 [m
[32m+[m[32mimport org.springframework.beans.factory.annotation.Value;[m
[32m+[m[32mimport org.springframework.context.annotation.Bean;[m
[32m+[m[32mimport org.springframework.context.annotation.Configuration;[m
[32m+[m[32mimport org.springframework.security.oauth2.jose.jws.MacAlgorithm;[m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.JwtDecoder;[m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.JwtEncoder;[m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.NimbusJwtDecoder;[m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.NimbusJwtEncoder;[m
[32m+[m
[32m+[m[32mimport javax.crypto.SecretKey;[m
[32m+[m[32mimport javax.crypto.spec.SecretKeySpec;[m
[32m+[m[32mimport java.util.Base64;[m
[32m+[m
[32m+[m[32mimport com.nimbusds.jose.jwk.JWKSet;[m
[32m+[m[32mimport com.nimbusds.jose.jwk.OctetSequenceKey;[m
[32m+[m[32mimport com.nimbusds.jose.jwk.source.ImmutableJWKSet;[m
[32m+[m[32mimport com.nimbusds.jose.proc.SecurityContext;[m
[32m+[m[32mimport com.nimbusds.jose.JWSAlgorithm;[m
[32m+[m[32mimport com.nimbusds.jose.jwk.KeyUse;[m
[32m+[m
[32m+[m[32m@Configuration[m
 public class JwtConfig {[m
[32m+[m
[32m+[m[32m    @Value("${app.jwt.secret}")[m
[32m+[m[32m    private String jwtSecret;[m
[32m+[m
[32m+[m[32m    @Bean[m
[32m+[m[32m    public SecretKey jwtSecretKey() {[m
[32m+[m[32m        // TODO 1: Decode the Base64 secret into bytes.[m
[32m+[m[32m        byte[] decodedSecret = Base64.getDecoder().decode(jwtSecret);[m
[32m+[m[32m        // TODO 2: Create and return a SecretKey using HmacSHA256.[m
[32m+[m[32m        return new SecretKeySpec(decodedSecret, "HmacSHA256");[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Bean[m
[32m+[m[32m    public JwtEncoder jwtEncoder(SecretKey jwtSecretKey) {[m
[32m+[m[32m        // TODO 3: Create a NimbusJwtEncoder using the secret key.[m
[32m+[m[32m        OctetSequenceKey jwk = new OctetSequenceKey.Builder(jwtSecretKey)[m
[32m+[m[32m                .keyID("store-management-key")[m
[32m+[m[32m                .algorithm(JWSAlgorithm.HS256)[m
[32m+[m[32m                .keyUse(KeyUse.SIGNATURE)[m
[32m+[m[32m                .build();[m
[32m+[m
[32m+[m[32m        ImmutableJWKSet<SecurityContext> jwkSource =[m
[32m+[m[32m                new ImmutableJWKSet<>(new JWKSet(jwk));[m
[32m+[m
[32m+[m[32m        return new NimbusJwtEncoder(jwkSource);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Bean[m
[32m+[m[32m    public JwtDecoder jwtDecoder(SecretKey jwtSecretKey) {[m
[32m+[m[32m        // TODO 4: Create a NimbusJwtDecoder using the secret key.[m
[32m+[m[32m        return NimbusJwtDecoder.withSecretKey(jwtSecretKey)[m
[32m+[m[32m                .macAlgorithm(MacAlgorithm.HS256)[m
[32m+[m[32m                .build();[m
[32m+[m[32m    }[m
 }[m
[1mdiff --git a/src/main/java/com/vdev/config/SecurityConfig.java b/src/main/java/com/vdev/config/SecurityConfig.java[m
[1mindex fdd48b8..028f3a3 100644[m
[1m--- a/src/main/java/com/vdev/config/SecurityConfig.java[m
[1m+++ b/src/main/java/com/vdev/config/SecurityConfig.java[m
[36m@@ -27,8 +27,9 @@[m [mpublic class SecurityConfig {[m
                         .requestMatchers(HttpMethod.POST,"/users/register").permitAll()[m
                         .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()[m
                 .anyRequest().authenticated())[m
[31m-                .httpBasic(Customizer.withDefaults()[m
[31m-                );[m
[32m+[m[32m                .oauth2ResourceServer([m
[32m+[m[32m                        oauth2 -> oauth2[m
[32m+[m[32m                                .jwt(Customizer.withDefaults()));[m
         return http.build();[m
      }[m
      @Bean[m
[1mdiff --git a/src/main/java/com/vdev/controller/AuthController.java b/src/main/java/com/vdev/controller/AuthController.java[m
[1mindex 90c039c..9909b77 100644[m
[1m--- a/src/main/java/com/vdev/controller/AuthController.java[m
[1m+++ b/src/main/java/com/vdev/controller/AuthController.java[m
[36m@@ -1,7 +1,9 @@[m
 package com.vdev.controller;[m
 [m
 import com.vdev.dto.LoginRequestDTO;[m
[32m+[m[32mimport com.vdev.service.JwtService;[m
 import jakarta.validation.Valid;[m
[32m+[m[32mimport org.springframework.security.core.Authentication;[m
 import org.springframework.web.bind.annotation.RestController;[m
 import org.springframework.web.bind.annotation.RequestMapping;[m
 import org.springframework.web.bind.annotation.PostMapping;[m
[36m@@ -13,18 +15,20 @@[m [mimport org.springframework.security.authentication.UsernamePasswordAuthenticatio[m
 @RequestMapping("/auth")[m
 public class AuthController {[m
     private final AuthenticationManager authenticationManager;[m
[32m+[m[32m    private final JwtService jwtService;[m
 [m
[31m-    public AuthController(AuthenticationManager authenticationManager) {[m
[32m+[m[32m    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {[m
         this.authenticationManager = authenticationManager;[m
[32m+[m[32m        this.jwtService = jwtService;[m
     }[m
     @PostMapping("/login")[m
[31m-    public void login([m
[32m+[m[32m    public String login([m
             @Valid @RequestBody LoginRequestDTO loginRequestDTO){[m
[31m-        authenticationManager.authenticate([m
[32m+[m
[32m+[m[32m       Authentication authentication = authenticationManager.authenticate([m
                 new UsernamePasswordAuthenticationToken([m
                         loginRequestDTO.getUsername(),[m
                         loginRequestDTO.getPassword()));[m
[32m+[m[32m        return jwtService.generateToken(authentication.getName());[m
     }[m
[31m-[m
[31m-[m
 }[m
[1mdiff --git a/src/main/java/com/vdev/service/JwtService.java b/src/main/java/com/vdev/service/JwtService.java[m
[1mindex 835f596..55b4f85 100644[m
[1m--- a/src/main/java/com/vdev/service/JwtService.java[m
[1m+++ b/src/main/java/com/vdev/service/JwtService.java[m
[36m@@ -1,4 +1,40 @@[m
[32m+[m
 package com.vdev.service;[m
 [m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.JwtEncoder;[m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.JwtClaimsSet;[m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.JwtEncoderParameters;[m
[32m+[m[32mimport org.springframework.stereotype.Service;[m
[32m+[m
[32m+[m[32mimport org.springframework.security.oauth2.jose.jws.MacAlgorithm;[m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.JwsHeader;[m
[32m+[m[32mimport org.springframework.security.oauth2.jwt.JwtEncoderParameters;[m
[32m+[m
[32m+[m[32mimport java.time.Instant;[m
[32m+[m
[32m+[m[32m@Service[m
 public class JwtService {[m
[32m+[m
[32m+[m[32m    private final JwtEncoder jwtEncoder;[m
[32m+[m
[32m+[m[32m    public JwtService(JwtEncoder jwtEncoder) {[m
[32m+[m[32m        this.jwtEncoder = jwtEncoder;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    // TODO: Implement a method that accepts an authenticated username[m
[32m+[m[32m    // and returns a signed JWT with subject, issued-at, and expiration claims.[m
[32m+[m[32m    public String generateToken(String username) {[m
[32m+[m[32m        Instant now = Instant.now();[m
[32m+[m
[32m+[m[32m        JwtClaimsSet claims = JwtClaimsSet.builder()[m
[32m+[m[32m                .subject(username)[m
[32m+[m[32m                .issuedAt(now)[m
[32m+[m[32m                .expiresAt(now.plusSeconds(900)) // Token valid for 15 minutes[m
[32m+[m[32m                .build();[m
[32m+[m[32m        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(claims);[m
[32m+[m
[32m+[m[32m        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();[m
[32m+[m
[32m+[m[32m        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();[m
[32m+[m[32m    }[m
 }[m
[1mdiff --git a/src/main/resources/application.properties b/src/main/resources/application.properties[m
[1mindex 268df46..a0efc30 100644[m
[1m--- a/src/main/resources/application.properties[m
[1m+++ b/src/main/resources/application.properties[m
[36m@@ -9,3 +9,5 @@[m [mspring.datasource.password=1122[m
 spring.jpa.hibernate.ddl-auto=validate[m
 spring.jpa.show-sql=true[m
 spring.jpa.properties.hibernate.format_sql=true[m
[32m+[m
[32m+[m[32mapp.jwt.secret=${STORE_JWT_SECRET}[m
\ No newline at end of file[m
