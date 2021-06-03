package com.example.road_owner_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //CSRF er disabled for at kunne sende post requests. Med det deaktiveret er der stor sikkerheds "fejl"
                //for at undgå skal post request sende en CSRF token med, men haringen anelse hvad eller hvordan XD
                .csrf().disable()
                .authorizeRequests(authorize -> {
            authorize.antMatchers("/", "/om", "/webjars/**", "/login", "/resources/**","/error","/error/**").permitAll()

                    .mvcMatchers("/dashboard", "/api/user/**").hasAuthority("USER")
                    .mvcMatchers("/api/admin/**", "/uploadFiles","/uploadFiles/**","/files","/sendEmail").hasAuthority("ADMIN")
                    .mvcMatchers("/files/public/**", "/css/**", "/images/**", "/js/**", "/api/public/**","/error","/error/**","/i", "/loggedIn").permitAll()
                    .mvcMatchers("/files/members/**", "/suggestions").hasAnyAuthority("ADMIN", "USER"); //TODO: OPS, authority instead of role

        })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/");
    }
}
