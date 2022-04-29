package com.rc.spring.security;

import com.rc.spring.security.entity.Role;
import com.rc.spring.security.entity.User;
import com.rc.spring.security.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CreateUserData implements CommandLineRunner {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) {
    addUsers();
  }

  /**
   * Create users if not exist in DB
   */
  private void addUsers() {
    if (!userService.checkUsersDataExistInDB()) {
      log.info("Save Users");
      userService.saveAll(createUsers());
    } else {
      log.info("Users are available in DB!");
    }
  }

  /**
   * Create User with Roles for testing purpose
   * @return List
   */
  private List<User> createUsers() {
    final List<User> users = new ArrayList<>();
    users.add(create("client",
        Collections.singletonList(Role.ROLE_CLIENT),"clientPass"));
    users.add(create("admin",
        Collections.singletonList(Role.ROLE_ADMIN),"adminPass"));
    users.add(create("supervisor",
        Collections.singletonList(Role.ROLE_SUPERVISOR),"superPass"));
    users.add(create("superUser",
        Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_SUPERVISOR),"superUserPass"));
    return users;
  }

  private User create(final String userName, final List<Role> roles, final String password) {
    return User.builder()
        .userName(userName)
        .password(passwordEncoder.encode(password))
        .active(true)
        .roles(roles)
        .build();
  }
}