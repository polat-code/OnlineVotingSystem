package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
