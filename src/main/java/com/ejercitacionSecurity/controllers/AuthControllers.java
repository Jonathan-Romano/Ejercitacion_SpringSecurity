package com.ejercitacionSecurity.controllers;

import com.ejercitacionSecurity.models.dtos.LoginDTO;
import com.ejercitacionSecurity.models.dtos.ResponseDTO;
import com.ejercitacionSecurity.persintence.entities.UserEntity;
import com.ejercitacionSecurity.services.IAuthService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControllers {
    
    @Autowired
    IAuthService authService;
    
    @PostMapping("/register")
    private ResponseEntity<ResponseDTO> register(@RequestBody UserEntity user) throws Exception{
        return new ResponseEntity<>(authService.register(user),HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    private ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO loginRequest) throws Exception{
        HashMap<String, String> login = authService.login(loginRequest);
        if(login.containsKey("jwt")){
            return new ResponseEntity<>(login, HttpStatus.OK);
        }else{
             return new ResponseEntity<>(login, HttpStatus.UNAUTHORIZED);
        }
    }
}
