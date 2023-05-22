package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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
    private Student student;



    @ManyToMany
    @JoinTable(
            name = "election_candidate",
            joinColumns = @JoinColumn(
                    name = "f_candidate_id",
                    referencedColumnName = "candidate_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="f_election_id",
                    referencedColumnName = "election_id"
            )
    )
    private List<com.example.onlinevotingsystem.models.Election> election;


}
