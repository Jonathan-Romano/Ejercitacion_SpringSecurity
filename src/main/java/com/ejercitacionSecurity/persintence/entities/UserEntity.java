
package com.ejercitacionSecurity.persintence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    
    @Column(name ="first_name")
    private String firstName;
    
    @Column(name= "last_name")
    private String lastName;
    
    private String email;
    
    private String password;

    public UserEntity() {
    }

    public UserEntity(Long id, String firstName, String lastName, String email, String pasword) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = pasword;
    }
    
    
    
}
