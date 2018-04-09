package com.example.userandtaskexam.repository;

import com.example.userandtaskexam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByEmail(String email);
}
