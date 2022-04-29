package com.rc.spring.security.service;

import com.rc.spring.security.dto.UserDto;
import com.rc.spring.security.entity.User;
import com.rc.spring.security.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Get total user count present in user table.
     * @return
     */
    public boolean checkUsersDataExistInDB() {
      return userRepository.count() > 0;
    }

    /**
     * Save user with Role in database
     * @param users
     */
    public void saveAll(final List<User> users) {
      userRepository.saveAll(users);
    }

    /**
     * It returns all users from the User Table from Database.
     * @return
     */
    public List<UserDto> getUsers() {
      log.info("Fetching all users from the db");
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
