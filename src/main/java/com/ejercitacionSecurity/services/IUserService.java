package com.ejercitacionSecurity.services;

import com.ejercitacionSecurity.persintence.entities.UserEntity;
import java.util.List;


public interface IUserService {
    public List<UserEntity> findfAllUSers();
}
