package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Representative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "representative_id", nullable = false)
    private Long representativeId;

    @OneToOne
    @JoinColumn(
            name = "f_candidate_id",
            referencedColumnName = "candidate_id"
    )
    private Candidate candidate;

}
