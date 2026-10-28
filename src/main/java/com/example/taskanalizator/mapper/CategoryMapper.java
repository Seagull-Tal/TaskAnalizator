package com.example.taskanalizator.mapper;

import com.example.taskanalizator.domain.CategoryEntity;
import com.example.taskanalizator.domain.dtos.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(CategoryEntity category);
    CategoryEntity toEntity(CategoryDTO categoryDTO);
}
