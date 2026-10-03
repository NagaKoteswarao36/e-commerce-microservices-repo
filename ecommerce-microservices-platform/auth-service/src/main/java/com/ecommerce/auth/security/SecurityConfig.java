package com.ecommerce.auth.security;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    UserDetailsService users(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(User.withUsername("admin").password(encoder.encode("admin123")).roles("ADMIN").build(), User.withUsername("user").password(encoder.encode("user123")).roles("USER").build());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(UserDetailsService uds, PasswordEncoder pe) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider(uds);
        p.setPasswordEncoder(pe);
        return new ProviderManager(p);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(c -> c.disable()).authorizeHttpRequests(a -> a.requestMatchers("/api/auth/login", "/api/auth/health", "/actuator/**").permitAll().anyRequest().authenticated()).build();
    }
}
