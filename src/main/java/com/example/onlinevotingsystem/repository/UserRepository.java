package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
