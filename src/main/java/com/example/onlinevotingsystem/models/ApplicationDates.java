package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Application_Dates")
public class ApplicationDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationDateId;
    private String applicationStartDate;
    private String applicationFinishDate;

}
