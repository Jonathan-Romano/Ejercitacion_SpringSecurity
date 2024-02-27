package com.ejercitacionSecurity.services.impl;

import com.ejercitacionSecurity.models.dtos.LoginDTO;
import com.ejercitacionSecurity.models.dtos.ResponseDTO;
import com.ejercitacionSecurity.models.validations.UserValidation;
import com.ejercitacionSecurity.persintence.entities.UserEntity;
import com.ejercitacionSecurity.persintence.repository.UserRepository;
import com.ejercitacionSecurity.services.IAuthService;
import com.ejercitacionSecurity.services.IJWTUtilityService;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements IAuthService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private IJWTUtilityService jwtUtilityService;
    
    @Autowired
    private UserValidation userValidation;
    
    @Override
    public HashMap<String, String> login(LoginDTO login) throws Exception{
        try{
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(login.getEmail());
            
            if(user.isEmpty()){
                jwt.put("Error", "User not registered!!");
                return jwt;
            }
        
            //Verificar contraseña
            if(verifyPassword(login.getPassword(), user.get().getPassword())){
                
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
                
            }else{
                jwt.put("Error", "Authentication failed");
            }
            return jwt;
            
        }catch(Exception e){
            throw new Exception("el error esta aca " + e.toString()  );
        }
        
    }
    
    @Override
    public ResponseDTO register(UserEntity user) throws Exception{
        try{
            ResponseDTO response = userValidation.validate(user);
            
            if(response.getNumOfErrors()>0){
               return response;
            }
            
            List<UserEntity> getAllUsers = userRepository.findAll();
            
            for(UserEntity repeatFields : getAllUsers){
                if(repeatFields.getEmail() == null ? user.getEmail() == null : repeatFields.getEmail().equals(user.getEmail())){
                    response.setNumOfErrors(1);
                    response.setMessage("User already exists!");
                    return response;
                }
            }
            
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            
            userRepository.save(user);
            
            response.setMessage("User created succefully!!!");
            
            return response;
        }catch(Exception e){
            throw new Exception(e.toString());
        }
    }
    
    private boolean verifyPassword(String enteredPassword, String storedPassword){
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        return encoder.matches(enteredPassword,  storedPassword);
    }
}
