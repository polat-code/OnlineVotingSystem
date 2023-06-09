package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.*;
import com.example.onlinevotingsystem.Dto.responses.LogInUserResponse;
import com.example.onlinevotingsystem.models.Department;
import com.example.onlinevotingsystem.models.Student;
import com.example.onlinevotingsystem.models.User;
import com.example.onlinevotingsystem.repository.DepartmentRepository;
import com.example.onlinevotingsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private StudentRepository studentRepository;
    private DepartmentRepository departmentRepository;

    public ResponseEntity<Object> isValidEmailOrNot(IsValidEmailRequest validEmailRequest) {
        Student student = studentRepository.findByEmail(validEmailRequest.getEmail());
        if(student != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> registerStudent(RegistrationRequest registrationRequest) {
        if(registrationRequest == null){
            return new ResponseEntity<>("Registration Request can not be null",HttpStatus.NOT_ACCEPTABLE);
        }
        Department department = departmentRepository.findByDepartmentName(registrationRequest.getDepartmentName());
        if(department == null){
            return  new ResponseEntity<>("There is no such a department " +registrationRequest.getDepartmentName(),HttpStatus.NOT_FOUND);
        }

        Student student = new Student();
        student.setName(registrationRequest.getName());
        student.setSurname(registrationRequest.getSurname());
        student.setStudentNumber(registrationRequest.getStudentNumber());
        student.setIsVoted(false);
        student.setEmail(registrationRequest.getEmail());
        student.setIsAdmin(registrationRequest.getIsAdmin());
        student.setDepartment(department);

        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public LogInUserResponse getUserByEmail(LogInUserRequest logInUserRequest) {
        Student student = studentRepository.findByEmail(logInUserRequest.getEmail());
        LogInUserResponse logInUserResponse = new LogInUserResponse().builder()
                .userId(student.getUserId())
                .name(student.getName())
                .surname(student.getSurname())
                .email(student.getEmail())
                .grade(student.getGrade())
                .isAdmin(student.getIsAdmin())
                .studentNumber(student.getStudentNumber())
                .build();

        return  logInUserResponse;
    }

    public ResponseEntity<Object> saveUser(SaveUserRequest saveUserRequest) {
        Student student = new Student();
        student.setName(saveUserRequest.getName());
        student.setSurname(saveUserRequest.getSurname());
        student.setEmail(saveUserRequest.getEmail());
        student.setIsAdmin(false);
        student.setDepartment(departmentRepository.findByDepartmentName(saveUserRequest.getDepartmentName()));
        student.setIsVoted(false);
        student.setGrade(saveUserRequest.getGrade());
        student.setStudentNumber(saveUserRequest.getStudentNumber());

        studentRepository.save(student);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
