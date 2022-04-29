package com.rc.spring.security.controller;

import com.rc.spring.security.dto.UserDto;
import com.rc.spring.security.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class MethodLevelSecurityController {

  private final UserService userService;

  @GetMapping("/anonymous")
  @ResponseStatus(HttpStatus.OK)
  public String accessToAllUsers() {
    log.info("Accessible to all users.");
    return "Welcome anonymous User";
  }

  @GetMapping("/client")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasRole('CLIENT')")
  public String testUserRoleAccess() {
    log.info("Accessible to CLIENT Role.");
    return "Welcome User (CLIENT)!";
  }

  @GetMapping("/admin")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasRole('ADMIN')")
  public String testAdminRoleAccess() {
    log.info("Accessible to ADMIN Role.");
    return "Welcome ADMIN User!";
  }

  @GetMapping("/supervisor")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasRole('SUPERVISOR')")
  public String testSupervisorsRoleAccess() {
    log.info("Accessible to SUPERVISOR Role.");
    return "Welcome SUPERVISOR User!";
  }

  @GetMapping("/admin-supervisor")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasRole('SUPERVISOR') AND hasRole('ADMIN')")
  public String testSupervisorAndAdminRoleAccess() {
    log.info("Accessible to ADMIN OR SUPERVISOR Role.");
    return "Welcome User (ADMIN OR SUPERVISOR)!";
  }

  @GetMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserDto> getAllUser() {
    log.info("fetching users");
    return userService.getUsers();
  }

}
