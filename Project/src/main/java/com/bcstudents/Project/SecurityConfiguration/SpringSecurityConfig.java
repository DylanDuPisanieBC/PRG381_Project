package com.bcstudents.Project.SecurityConfiguration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bcstudents.Project.Services.AdministratorService;
import com.bcstudents.Project.Services.StudentService;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdministratorService administratorService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider studentAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(studentService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationProvider administratorAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(administratorService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(studentAuthenticationProvider());
        providers.add(administratorAuthenticationProvider());

        return new ProviderManager(providers);
    }

    //security filter chain checks the http request from the client
    //if requested route matches http request it checks permissions for that route, if the permission matches it checks the controllers for that route

    @Bean
    public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login", "/register","/redirectByRole").permitAll()
                        .requestMatchers("/student/**").hasRole("USER") // Define the URL pattern for USER role
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Define the URL pattern for ADMIN role
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login") // spring has a default login form -> this overrides it and displays the form that is chosen
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/redirectByRole", true) // if login is succesfull it redirects users to redirectByRole route
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll());
        return http.build();
    }
}
