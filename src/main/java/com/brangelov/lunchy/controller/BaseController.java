package com.brangelov.lunchy.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    private ModelMapper modelMapper;

    protected <TS, TD> TD map(TS source, Class<TD> type) {
        return modelMapper.map(source, type);
    }

    protected <TS, TD> void map(TS source, TD destination) {
        modelMapper.map(source, destination);
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
