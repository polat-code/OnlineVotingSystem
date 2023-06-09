package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Candidate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id", nullable = false)
    private Long candidateId;

    @Column(name = "vote_count")
    @ColumnDefault("0")
    private int voteCount;

    @Column(name = "is_canceled")
    @ColumnDefault("false")
    private Boolean isCanceled;

    @OneToOne
    @JoinColumn(
            name = "f_user_id",
            referencedColumnName = "user_id"
    )
    private Student student;



    @ManyToMany
    @JoinTable(
            name = "candidatesForElection",
            joinColumns = @JoinColumn(
                    name = "f_candidate_id",
                    referencedColumnName = "candidate_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="f_election_id",
                    referencedColumnName = "election_id"
            )
    )
    private List<Election> elections;

    @OneToMany(mappedBy = "votedFor")
    private List<Student> votedForStudent;


}
