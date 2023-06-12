package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.Dto.requests.CreateFacultyRequest;
import com.example.onlinevotingsystem.Dto.requests.UpdateFacultyRequest;
import com.example.onlinevotingsystem.Dto.responses.GetAllFacultyResponse;
import com.example.onlinevotingsystem.services.FacultyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
@AllArgsConstructor
@CrossOrigin(origins = "https://votingsystem.herokuapp.com")
//@CrossOrigin(origins =  "http://localhost:3000")
public class FacultyController {

    private FacultyService facultyService;

    @GetMapping("")
    public List<GetAllFacultyResponse> getAll(){
        return this.facultyService.getAll();

    }

    @PostMapping("")
    public void addFaculty(@RequestBody CreateFacultyRequest createFacultyRequest){

        this.facultyService.addFaculty(createFacultyRequest);
    }

    @DeleteMapping("{facultyId}")
    public void deleteFaculty(@PathVariable() Long facultyId){
        this.facultyService.deleteFaculty(facultyId);
    }

    @PutMapping("")
    public void updateFaculty(@RequestBody UpdateFacultyRequest updateFacultyRequest){
        this.facultyService.updateFaculty(updateFacultyRequest);
    }
}
