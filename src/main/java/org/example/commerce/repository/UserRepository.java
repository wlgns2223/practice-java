package org.example.commerce.repository;

import org.example.commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean findUserByEmail(String email);
}
