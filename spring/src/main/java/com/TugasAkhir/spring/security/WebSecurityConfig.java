package com.TugasAkhir.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/auth/login-sso", "/auth/validate-ticket").permitAll()
                .antMatchers("/user/doctor/*", "/user/apothecary/*", "/user/patient/*").hasAuthority("ADMIN")
                .antMatchers("/appointment/detail/**").hasAnyAuthority("ADMIN", "DOCTOR", "PATIENT")
                .antMatchers("/prescription/detail/**").hasAnyAuthority("ADMIN", "DOCTOR", "APOTHECARY","PATIENT")
                .antMatchers("/prescription/view-all").hasAnyAuthority("ADMIN", "APOTHECARY")
                .antMatchers("/appointment/save/**").hasAuthority("DOCTOR")
                .antMatchers("/prescription/add/**").hasAuthority("DOCTOR")
                .antMatchers("/prescription/save/**").hasAuthority("APOTHECARY")
                .antMatchers("/login-sso", "/validate-ticket").permitAll()
                .antMatchers("/admin", "/statistics").hasAuthority("ADMIN")                                //Cuma contoh buat ngasih akses
                .antMatchers("/admin_doctor").hasAnyAuthority("ADMIN", "DOCTOR") // Ada di main cntroller
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

}
