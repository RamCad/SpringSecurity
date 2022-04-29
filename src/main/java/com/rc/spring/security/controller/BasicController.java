package com.rc.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

  @GetMapping("/spring-security")
  public String userRole() {
    return "Spring Security!";
  }

  @GetMapping("/admin/spring-security")
  public String adminRole() {
    return "Spring Security Admin!";
  }

}
