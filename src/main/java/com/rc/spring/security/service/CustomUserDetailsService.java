package com.rc.spring.security.service;

import com.rc.spring.security.entity.CustomUserDetails;
import com.rc.spring.security.entity.User;
import com.rc.spring.security.repository.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepository.findByUserName(username);
    return optionalUser.map(CustomUserDetails::new).orElseThrow(
        () -> new UsernameNotFoundException(String.format("User = %s does not exists", username)));

  }
}
