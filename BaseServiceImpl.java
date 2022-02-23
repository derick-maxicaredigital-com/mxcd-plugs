package com.maxicaredigital.@model.services;

import com.maxicaredigital.@model.entity.@ModelEntity;
import com.maxicaredigital.@model.model.@Model;
import com.maxicaredigital.@model.repository.@ModelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class @ModelServiceImpl implements @ModelService{

    private @ModelRepository @modelRepository;

    public @ModelServiceImpl(@ModelRepository @modelRepository) {
        this.@modelRepository = @modelRepository;
    }

    @Override
    public @Model create@Model(@Model @model) {
        @ModelEntity @modelEntity = new @ModelEntity();

        BeanUtils.copyProperties(@model, @modelEntity);
        @modelRepository.save(@modelEntity);
        return @model;
    }

    @Override
    public List<@Model> getAll@Models() {
        List<@ModelEntity> @modelEntities
                = @modelRepository.findAll();

        List<@Model> @models = @modelEntities
                .stream()
                .map(emp -> new @Model(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmailId()))
                .collect(Collectors.toList());
        return @models;
    }

    @Override
    public boolean delete@Model(Long id) {
        @ModelEntity @model = @modelRepository.findById(id).get();
        @modelRepository.delete(@model);
        return true;
    }

    @Override
    public @Model get@ModelById(Long id) {
        @ModelEntity @modelEntity
                = @modelRepository.findById(id).get();
        @Model @model = new @Model();
        BeanUtils.copyProperties(@modelEntity, @model);
        return @model;
    }

    @Override
    public @Model update@Model(Long id, @Model @model) {
        @ModelEntity @modelEntity
                = @modelRepository.findById(id).get();
        @modelEntity.setEmailId(@model.getEmailId());
        @modelEntity.setFirstName(@model.getFirstName());
        @modelEntity.setLastName(@model.getLastName());

        @modelRepository.save(@modelEntity);
        return @model;
    }
}
