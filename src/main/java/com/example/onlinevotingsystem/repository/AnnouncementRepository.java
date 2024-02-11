package com.example.onlinevotingsystem.repository;

import com.example.onlinevotingsystem.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository  extends JpaRepository<Announcement,Long> {
}
