package com.todoapp.todo.service;

import com.todoapp.todo.entity.User;
import com.todoapp.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addNewUser(User user) throws Exception {
        Optional<User> opt = userRepository.findByUserName(user.getUserName());
        if(opt.isPresent()) throw new Exception();
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }
}
