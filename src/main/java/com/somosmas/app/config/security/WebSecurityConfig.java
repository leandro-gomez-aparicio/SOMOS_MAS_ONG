package com.somosmas.app.config.security;

import com.somosmas.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .disable()
                .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/auth/**",
                        "/register",
                        "/login",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/api/docs",
                        "/api/docs/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/news",
                        "/activities",
                        "/organization/public",
                        "/api/docs",
                        "/posts/{id}/comments").permitAll()
                .antMatchers("/activities/**",
                        "/categories/**",
                        "/news/**",
                        "/users/**",
                        "/slides",
                        "/slides/**",
                        "/testimonials",
                        "/testimonials/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,
                        "/members").hasRole("ADMIN")
                // comments path
                .antMatchers(HttpMethod.POST,
                        "/comments").hasRole("USER")
                .antMatchers(HttpMethod.PUT,
                        "/comments").hasRole("USER")
                .antMatchers(HttpMethod.GET,
                "/comments").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/comments").hasAnyRole("ADMIN","USER")
                // end comment path
                .anyRequest().hasAnyRole("ADMIN","USER");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    } 

}
