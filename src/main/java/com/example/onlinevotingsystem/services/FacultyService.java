package com.example.onlinevotingsystem.services;

import com.example.onlinevotingsystem.Dto.requests.CreateFacultyRequest;
import com.example.onlinevotingsystem.Dto.requests.UpdateFacultyRequest;
import com.example.onlinevotingsystem.Dto.responses.GetAllFacultyResponse;
import com.example.onlinevotingsystem.core.utilities.abstracts.ModelMapperService;
import com.example.onlinevotingsystem.models.Faculty;
import com.example.onlinevotingsystem.repository.FacultyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FacultyService {

    private FacultyRepository facultyRepository;

    private ModelMapperService modelMapperService;

    public void addFaculty(CreateFacultyRequest createFacultyRequest){



        boolean flag = facultyRepository.existsByFacultyName(createFacultyRequest.getFacultyName());
        if(!flag){
            Faculty faculty = this.modelMapperService.forRequest().map(createFacultyRequest,Faculty.class);
            this.facultyRepository.save(faculty);
        }
    }


    public void deleteFaculty(Long facultyId){

        if(this.facultyRepository.existsById(facultyId)){
            facultyRepository.deleteById(facultyId);
        }
    }


    public void updateFaculty(UpdateFacultyRequest updateFacultyRequest) {

        if(this.facultyRepository.findByFacultyName(updateFacultyRequest.getFacultyName()).equals(true))
        {
            Faculty faculty=this.modelMapperService.forRequest().map(updateFacultyRequest,Faculty.class);
            this.facultyRepository.save(faculty);
        }

    }

    public List<GetAllFacultyResponse> getAll() {

        List<Faculty> faculties = facultyRepository.findAll();

        return faculties.
                stream()
                .map(faculty ->
                        this.modelMapperService.
                                forResponse().
                                map(faculties,GetAllFacultyResponse.class)).toList();
    }
}
