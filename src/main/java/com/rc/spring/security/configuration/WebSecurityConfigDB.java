package com.rc.spring.security.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigDB extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/user/*").hasAnyRole("USER","ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().permitAll()
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403");

    http.csrf().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    //it'll delegate to NoOpPasswordEncoder
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Autowired
  private DataSource dataSource;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception{
    return  super.authenticationManagerBean();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(dataSource).passwordEncoder(passwordEncoder())
        // set credential from database
        .usersByUsernameQuery("select username,password,enabled from users where username=?")
        //set authorities from database
        .authoritiesByUsernameQuery("select username,authority from authorities where username=?");
  }

}
