package com.example.gym.Config;

import com.example.gym.Filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {
    private UserDetailsService userDetailsService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private AuthenticationEntryPoint authenticationEntryPoint;


    public WebSecurityConfiguration(UserDetailsService userDetailsService,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          AuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf( (csrf) -> {
            try {
                csrf.disable().authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(HttpMethod.GET,"/api/v1/***").permitAll()
                                .requestMatchers(HttpMethod.POST , "api/v1/auth/***").permitAll()
                                .anyRequest().permitAll()
                ).exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}