package com.spring.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MyConfigSecurity {

    @Bean
    //Authentication
    public UserDetailsService userDetailsService() {

      /*  UserDetails userDetails = User.withUsername("Ajit")
                .password(encoder.encode("Ajit"))
                .roles("ADMIN")
                .build();
        UserDetails userDetails1 = User.withUsername("basant")
                .password(encoder.encode("123"))
                .roles("NORMAL")
                .build();
        UserDetails userDetails2 = User.withUsername("User")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails, userDetails1, userDetails2);*/

        return new UserInfoUserDetailsService();
    }

    @Bean
    //Authorization
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/home/admin", "/products/new", "/products/welcome")
                .permitAll()
                // .anyRequest()
                // .authenticated()
                .and()
                .authorizeHttpRequests().requestMatchers("/home/**", "/products/**")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}

