package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Student extends User{

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
     */

    @Column(name = "student_number")
    private String studentNumber;

    @OneToOne(mappedBy = "student")
    private Application application;

    // Add Department
    @ManyToOne
    @JoinColumn(
            name = "f_department_id",
            referencedColumnName = "department_id"
    )
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "voters",
            joinColumns = @JoinColumn(
                    name = "f_user_id",
                    referencedColumnName = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="f_election_id",
                    referencedColumnName = "election_id"
            )
    )
    private List<Election> elections;

    private Boolean isVoted;

    @ManyToOne
    @JoinColumn(
            name = "f_candidate_id",
            referencedColumnName = "candidate_id"
    )
    private Candidate votedFor;



}
