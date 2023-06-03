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
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<User> users;

    @ManyToOne
    @JoinColumn(
            name = "f_faculty_id",
            referencedColumnName = "faculty_id"
    )
    private Faculty faculty;



}
