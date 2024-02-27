
package com.ejercitacionSecurity.controllers;

import com.ejercitacionSecurity.persintence.entities.UserEntity;
import com.ejercitacionSecurity.services.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    
    @GetMapping("/find-all")
    private ResponseEntity<List<UserEntity>> getAllUser(){
        return new ResponseEntity<>(userService.findfAllUSers(),  HttpStatus.OK);
    }
}
