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


}
