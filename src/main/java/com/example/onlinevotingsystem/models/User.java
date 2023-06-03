package com.example.onlinevotingsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User implements UserDetails {


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


    @Column(name = "student_number")
    private String studentNumber;

    @OneToOne(mappedBy = "user")
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
