package com.example.onlinevotingsystem.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetResultResponse {

    private Map<String,Integer> result;
    private Long electionId;
    private String deptName;
}
