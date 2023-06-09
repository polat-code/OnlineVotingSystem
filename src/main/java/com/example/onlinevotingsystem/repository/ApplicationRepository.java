package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository  extends JpaRepository<Application,Long> {

   @Query(value = "SELECT * FROM Application where f_user_id = ?1",
   nativeQuery = true)
    Application findApplicationByUserId(Long userId);


}
