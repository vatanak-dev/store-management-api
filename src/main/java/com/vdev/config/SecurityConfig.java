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


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .anyRequest().authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2
                                .jwt(Customizer.withDefaults()));
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

}
