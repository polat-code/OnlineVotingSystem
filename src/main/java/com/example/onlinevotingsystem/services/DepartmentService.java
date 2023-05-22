package com.example.onlinevotingsystem.services;


import com.example.onlinevotingsystem.Dto.requests.CreateDepartmentRequest;
import com.example.onlinevotingsystem.core.utilities.abstracts.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentService {


    private DepartmentRepository departmentRepository;

    private ModelMapperService modelMapperService;

    public void addDepartment(CreateDepartmentRequest createDepartmentRequest) {


    }
}
