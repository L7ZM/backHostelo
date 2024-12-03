package com.udev.hotel.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.udev.hotel.config.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

	 private final AuthenticationProvider authenticationProvider;
	 private JwtAuthenticationFilter jwtAuthenticationFilter;

	 public SecurityConfig(
		        JwtAuthenticationFilter jwtAuthenticationFilter,
		        AuthenticationProvider authenticationProvider
		    ) {
		        this.authenticationProvider = authenticationProvider;
		        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		    }
	 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers(
                		"/auth/signup",
                		"/auth/login",
                		"/api/user/userProfile",
                		"/api/admin/adminProfile",
                		"/api//users/{email:\" + Constants.LOGIN_REGEX + \"}",
                		"/api/welcome",
                		"/api/users",
                		"/api/register",
                		"/api/**",
                		"/api/user/{id}",
                		"/api/user/{idUser}",
                		"/api/reservations",
                    "/api/users/authorities",
                    "/api/chambres/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/swagger-resources/configuration/ui",
                    "/swagger-resources/configuration/security",
                    "/webjars/**"
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
