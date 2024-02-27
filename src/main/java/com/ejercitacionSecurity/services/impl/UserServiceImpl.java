package com.ejercitacionSecurity.services.impl;

import com.ejercitacionSecurity.persintence.entities.UserEntity;
import com.ejercitacionSecurity.persintence.repository.UserRepository;
import com.ejercitacionSecurity.services.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findfAllUSers() {
        return userRepository.findAll();
    }
}
