package com.rc.spring.security.configuration;

import com.rc.spring.security.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityMethodLevel extends WebSecurityConfigurerAdapter {

  private static final String[] WHITELIST_PATTERNS = {"/anonymous", "/h2-console/**"};

  private final CustomUserDetailsService detailsService;

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(detailsService);
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.httpBasic()
        .and().authorizeRequests().antMatchers(WHITELIST_PATTERNS).permitAll()
        .and().authorizeRequests().anyRequest().authenticated()
        .and().csrf().disable().headers().frameOptions().sameOrigin();
    //here we will not create a session
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
