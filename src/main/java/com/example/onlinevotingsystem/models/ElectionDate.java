package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "election_dates")
public class ElectionDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long electionId;

    private String electionStartDate;
    private String electionFinishDate;
    private String createdAt;

}
