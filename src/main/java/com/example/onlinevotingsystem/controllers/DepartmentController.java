package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateDepartmentRequest;
import com.example.onlinevotingsystem.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;


    @PostMapping("")
    public void addDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest){

        this.departmentService.addDepartment(createDepartmentRequest);


    }

}
