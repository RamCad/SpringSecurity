package com.rc.spring.security.dto;

import com.rc.spring.security.entity.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

  private int id;
  private String userName;
  private boolean isActive;
  private List<Role> roles;
}
