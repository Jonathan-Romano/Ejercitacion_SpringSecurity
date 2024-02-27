package com.ejercitacionSecurity.services;

import com.ejercitacionSecurity.models.dtos.LoginDTO;
import com.ejercitacionSecurity.models.dtos.ResponseDTO;
import com.ejercitacionSecurity.persintence.entities.UserEntity;
import java.util.HashMap;


public interface IAuthService {
    public HashMap<String, String> login(LoginDTO login) throws Exception;

    public ResponseDTO register(UserEntity user) throws Exception;
}


