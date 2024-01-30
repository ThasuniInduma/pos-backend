package com.thasuniinduma.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thasuniinduma.backend.dto.UserPwdDto;
import com.thasuniinduma.backend.entity.User;

@Service
public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User changeUserPassword(Long id, UserPwdDto userPwdDto);
    
}
