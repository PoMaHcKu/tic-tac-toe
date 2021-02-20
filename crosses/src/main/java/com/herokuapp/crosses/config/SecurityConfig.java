package com.herokuapp.crosses.config;

import com.herokuapp.crosses.model.persist.Role;
import com.herokuapp.crosses.model.persist.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        User first = User.builder()
                .login("1")
                .password(encoder.encode("1"))
                .role(Role.PLAYER)
                .build();
        User second = User.builder()
                .login("2")
                .password(encoder.encode("2"))
                .role(Role.PLAYER)
                .build();

        InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
        detailsManager.createUser(first);
        detailsManager.createUser(second);

        return detailsManager;
    }
}