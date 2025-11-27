package org.example.commerce.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;
import org.example.commerce.constant.Role;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private String username;
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; // admin or user

    @Builder
    public User(String username,String password, String email,Role role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

    }

    public void updateUsername(String username){
        this.username = username;
    }

    public  void updatePassword(String password){
        this.password = password;
    }

    public void updateEmail(String email){
        this.email = email;
    }

    public void updateRole(Role role){
        this.role = role;
    }

}
