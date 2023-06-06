package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateDepartmentRequest;
import com.example.onlinevotingsystem.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {

    private DepartmentService departmentService;


    @PostMapping("")
    public void addDepartment(@RequestBody CreateDepartmentRequest createDepartmentRequest){

        this.departmentService.addDepartment(createDepartmentRequest);


    }

    @DeleteMapping("/{departmentId}")
    public void deleteDepartment(@PathVariable("departmentId") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
    }


}
