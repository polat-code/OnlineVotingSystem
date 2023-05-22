package com.example.onlinevotingsystem.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
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

    @Column(name = "election_date")
    private String electionDate;

    @Column(name = "election_name")
    private String electionName;

    @OneToOne
    @JoinColumn(
            name = "f_department_id",
            referencedColumnName = "department_id"
    )
    private Department department;


    @ManyToMany(mappedBy = "election")
    private List<Student> students;

    @ManyToMany(mappedBy = "election")
    private List<Candidate> candidates;




}
