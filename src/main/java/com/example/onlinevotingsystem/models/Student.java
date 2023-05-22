package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;


@Entity

public class Student extends User{

    @Column(name = "student_id")
    private Long studentId;

    @OneToOne
    @JoinColumn(
            name = "f_user_id",
            referencedColumnName = "user_id"
    )
    private User user;

    @OneToOne
    @JoinColumn(
            name = "f_application_id",
            referencedColumnName = "application_id"
    )
    private Application applicationId;

    @ManyToOne
    @JoinColumn(
            name = "f_election_id",
            referencedColumnName = "election_id"
    )
    private Election election;


}
