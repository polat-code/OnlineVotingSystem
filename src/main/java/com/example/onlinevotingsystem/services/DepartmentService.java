package com.example.onlinevotingsystem.services;


import com.example.onlinevotingsystem.Dto.requests.CreateDepartmentRequest;
import com.example.onlinevotingsystem.core.utilities.abstracts.ModelMapperService;
import com.example.onlinevotingsystem.exceptions.InvalidFacultyId;
import com.example.onlinevotingsystem.models.Department;
import com.example.onlinevotingsystem.models.Faculty;
import com.example.onlinevotingsystem.repository.DepartmentRepository;
import com.example.onlinevotingsystem.repository.FacultyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentService {


    private DepartmentRepository departmentRepository;

    private ModelMapperService modelMapperService;
    private FacultyRepository facultyRepository;

    public void addDepartment(CreateDepartmentRequest createDepartmentRequest) {

        Faculty faculty = null;
        try {
            faculty = facultyRepository.findById(createDepartmentRequest.getFacultyId()).get();
            if(faculty == null) {
                throw new InvalidFacultyId("There is no such a faculty id");
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        var department = new Department().builder()
                .departmentName(createDepartmentRequest.getDepartmentName())
                .faculty(faculty).build();
        departmentRepository.save(department);

    }

    public void deleteDepartment(Long departmentId) {
        try {
            Department department = departmentRepository.findById(departmentId).orElseThrow();
            departmentRepository.deleteById(department.getDepartmentId());
        }catch (Exception e) {
            System.out.println(e);
        }

    }
}
