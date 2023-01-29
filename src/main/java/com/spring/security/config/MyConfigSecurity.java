package com.spring.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableMethodSecurity
public class MyConfigSecurity {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails userDetails = User.withUsername("Ajit")
                .password(encoder.encode("Ajit"))
                .roles("ADMIN")
                .build();
        UserDetails userDetails1 = User.withUsername("basant")
                .password(encoder.encode("123"))
                .roles("NORMAL")
                .build();

        return new InMemoryUserDetailsManager(userDetails, userDetails1);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/home/admin")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

