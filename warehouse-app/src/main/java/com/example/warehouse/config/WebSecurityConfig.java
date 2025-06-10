package com.example.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/techniques", true) 
                .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
        return http.build();
    }
    
    @Bean public UserDetailsService userDetailsService() {
        var user = User.builder().username("user").password(passwordEncoder().encode("password")).roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
    @Bean public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
}
