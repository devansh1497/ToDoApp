package com.todoapp.todo.jwt;

import com.todoapp.todo.entity.User;
import com.todoapp.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
  @Autowired
  private UserRepository userRepository;

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
        "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(2L, "ranga",
            "$2a$10$R7BJmWGDuNZnQYU5EM/Qc.y8ez1e/P80oIBeaILMMigHaI.0eUWlC","ROLE_USER_2"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOpt = userRepository.findByUserName(username);

    if(!userOpt.isPresent()) throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    User user = userOpt.get();

    return new JwtUserDetails(1L,user.getUserName(),user.getPassword(),"ROLE");
  }

}


