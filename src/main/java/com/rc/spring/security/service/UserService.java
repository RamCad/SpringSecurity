package com.rc.spring.security.service;

import com.rc.spring.security.dto.UserDto;
import com.rc.spring.security.entity.User;
import com.rc.spring.security.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean checkUsersDataExistInDB() {
      return userRepository.count() > 0;
    }

    public void saveAll(final List<User> users) {
      userRepository.saveAll(users);
    }

    public List<UserDto> getUsers() {
      final List<User> users = userRepository.findAll();
      if (CollectionUtils.isEmpty(users)) {
        log.info("No users in DB!");
        return Collections.emptyList();
      }
      return users.stream()
          .map(user -> new UserDto(user.getId(), user.getUserName(), user.isActive(),
              user.getRoles())).collect(Collectors.toList());
    }

}
