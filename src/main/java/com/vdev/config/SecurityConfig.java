package com.vdev.config;

import com.vdev.repository.UserRepository;
import com.vdev.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.DELETE,"/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/products/**").hasAnyRole("ADMIN","STAFF")
                        .requestMatchers(HttpMethod.PUT,"/products/**").hasAnyRole("ADMIN","STAFF")
                        .requestMatchers(HttpMethod.PATCH,"/products/**").hasAnyRole("ADMIN","STAFF")
                        .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()

                .anyRequest().authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2
                                .jwt(jwt -> jwt
                                        .jwtAuthenticationConverter(jwtAuthConverter()))
                );
        return http.build();
     }
     @Bean
    public UserDetailsService userDetailsService(
            UserRepository userRepository){
        return new CustomUserDetailsService(userRepository);
     }
     @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
     }

     @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthConverter (){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthConverter =
                new JwtGrantedAuthoritiesConverter();

        jwtGrantedAuthConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthConverter =
                new JwtAuthenticationConverter();

        jwtAuthConverter.setJwtGrantedAuthoritiesConverter(
                jwtGrantedAuthConverter);

        return jwtAuthConverter;
    }

}
