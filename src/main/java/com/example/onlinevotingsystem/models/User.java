package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;


    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @Column(name = "profile_photo_path")
    private String profilePhotoPath;

    @Column(name = "grade")
    private int grade;

}
