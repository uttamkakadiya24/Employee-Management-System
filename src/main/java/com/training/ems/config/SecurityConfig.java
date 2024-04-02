package com.training.ems.config;

import com.training.ems.security.JwtAuthenticationEntryPoint;
import com.training.ems.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.training.ems.permissions.Permission.*;
import static com.training.ems.permissions.Role.*;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint point;
    private final JwtAuthenticationFilter filter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorization -> authorization

                        .requestMatchers("/employee/**").hasAnyRole(EMPLOYEE.name())
                        .requestMatchers(HttpMethod.GET,("/employee/**")).hasAnyAuthority(EMPLOYEE_READ.name())
                        .requestMatchers(HttpMethod.POST,("/employee/**")).hasAnyAuthority(EMPLOYEE_CREATE.name())
                        .requestMatchers(HttpMethod.PUT,("/employee/**")).hasAnyAuthority(EMPLOYEE_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE,("/employee/**")).hasAnyAuthority(EMPLOYEE_DELETE.name())

                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.GET,("/admin/**")).hasAuthority(ADMIN_READ.name())
                        .requestMatchers(HttpMethod.POST,("/admin/**")).hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(HttpMethod.PUT,("/admin/**")).hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE,("/admin/**")).hasAuthority(ADMIN_DELETE.name())

                        .requestMatchers("/manager/**").hasAnyRole(MANAGER.name(),ADMIN.name())
                        .requestMatchers(HttpMethod.GET,("/manager/**")).hasAnyAuthority(MANAGER_READ.name())
                        .requestMatchers(HttpMethod.POST,("/manager/**")).hasAnyAuthority(MANAGER_CREATE.name())
                        .requestMatchers(HttpMethod.PUT,("/manager/**")).hasAnyAuthority(MANAGER_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE,("/manager/**")).hasAnyAuthority(MANAGER_DELETE.name())

                        .requestMatchers("/auth/login")
                        .permitAll()
                        .requestMatchers("/auth/employee-signup")
                        .permitAll()
                        .requestMatchers("/auth/admin-signup")
                        .permitAll()
                        .requestMatchers("/auth/manager-signup")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(ex->ex.authenticationEntryPoint(point))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthenticationProvider())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class )
        ;
        return http.build();

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
