package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @OneToOne
    @JoinColumn(
            name = "f_application_id",
            referencedColumnName = "application_id"
    )
    private Application applicationId;




}
