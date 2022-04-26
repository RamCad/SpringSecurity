package com.rc.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

  @GetMapping("/spring-security")
  public String testSecurity() {
    return "Spring Security!";
  }

}
