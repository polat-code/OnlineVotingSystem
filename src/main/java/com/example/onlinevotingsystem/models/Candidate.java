package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Candidate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id", nullable = false)
    private Long candidateId;

    @Column(name = "vote_count")
    private int voteCount;

    @OneToOne
    @JoinColumn(
            name = "f_user_id",
            referencedColumnName = "user_id"
    )
    private User user;
}
