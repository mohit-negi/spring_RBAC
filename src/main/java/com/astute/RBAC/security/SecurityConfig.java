package com.astute.RBAC.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private MyUserDetailService myUserDetailService;
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public SecurityConfig(MyUserDetailService myUserDetailService,AuthenticationEntryPoint authenticationEntryPoint) {
        this.myUserDetailService = myUserDetailService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests(configurer->
//                configurer
//                        .requestMatchers(HttpMethod.GET,"/api/home/").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET,"/api/home/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET,"/api/home/").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/api/home/").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/api/home/").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/api/home/").hasRole("SUPER_ADMIN")
//        );
//
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        httpSecurity.csrf(csrf -> csrf.disable());
//        return httpSecurity.build();
//
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests
                // Allow all requests to "/api/auth/**"
                .requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll()
                // Restrict other endpoints, such as "/api/user/**", to authenticated users
                .requestMatchers(new AntPathRequestMatcher("/api/user/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/api/auth/user/**")).authenticated()

                // Ensure a default rule for all remaining requests (optional for clarity)
                .anyRequest().authenticated()
        );
        httpSecurity.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(new JwtAuthEntryPoint()));
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Configure authentication and other security setting
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
}

