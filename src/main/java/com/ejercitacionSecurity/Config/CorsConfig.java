package com.ejercitacionSecurity.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig  implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry){
        
        /*Config de rutas que necesitan authorizacion y los metodos que puede usar*/
        registry.addMapping("/**") /*Rutas privadas*/
                .allowedOrigins("http://localHost:4200")/*Ruta del frontEnd*/
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") /*Que metodos tiene permitido usar*/
                .allowedHeaders("Origin", "Content-Type", "Accept", "Athorization")
                .allowCredentials(true) /*Esto hace que sea privada*/
                .maxAge(3600);
        
        
        /*Config de rutas publicas y sus metodos*/
        registry.addMapping("/auth/**") /*Rutas publicas*/
                .allowedOrigins("http://localHost:4200") /*Ruta del frontEnd*/ /* si se pone un  "*" acepta todas las url*/
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") /*Que metodos tiene permitido usar*/ /* si se pone un  "*" acepta todos los metodos*/
                .allowedHeaders("Origin", "Content-Type", "Accept", "Athorization")
                .allowCredentials(false)/*Esto hace que sea publica*/
                .maxAge(3600);
    }
    
}
