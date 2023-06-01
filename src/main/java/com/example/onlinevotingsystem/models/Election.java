package com.example.onlinevotingsystem.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "election_id", nullable = false)
    private Long electionId;

    @Column(name = "election_start_date")
    private String electionStartDate;

    @Column(name = "election_finish_date")
    private String electionFinishDate;

    @Column(name = "election_name")
    private String electionName;

    @OneToOne
    @JoinColumn(
            name = "f_department_id",
            referencedColumnName = "department_id"
    )
    private Department department;


    @ManyToMany(mappedBy = "elections")
    private List<Student> students;

    @ManyToMany(mappedBy = "elections")
    private List<Candidate> candidates;




}
