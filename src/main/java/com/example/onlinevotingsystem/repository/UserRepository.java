package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{



    @Query("SELECT u.email FROM User u")
    List<String> getAllEmails();

    Optional<User> findByEmail(String username);
}
