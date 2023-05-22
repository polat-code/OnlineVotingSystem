package com.example.onlinevotingsystem.core.utilities.abstracts;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
