package com.example.onlinevotingsystem.repositories;

import com.example.onlinevotingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
