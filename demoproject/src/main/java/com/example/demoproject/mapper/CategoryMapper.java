package com.example.demoproject.mapper;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper implements EntityMapper<Category, CategoryDTO> {
    @Override
    public Category toEntity(CategoryDTO dto) {
//        Timestamp createdDate = DateUtils.stringToTimestamp(dto.getCreatedDate());
//        Timestamp updatedDate = DateUtils.stringToTimestamp(dto.getUpdatedDate());

        return Category.builder()
                .id(dto.getId())
                .idparent(dto.getIdparent())
                .name(dto.getName())
                .notes(dto.getNotes())
                .icon(dto.getIcon())
                .createdDate(dto.getCreatedDate())
                .createdDate(dto.getCreatedDate())
//                .createdDate(createdDate)
//                .updatedDate(updatedDate)
                .createdBy(dto.getCreatedBy())
                .updatedBy(dto.getUpdatedBy())
                .isactive(dto.getIsactive())
                .build();
    }

    @Override
    public CategoryDTO toDto(Category entity) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String createdDateString = dateFormat.format(entity.getCreatedDate());
//        String updatedDateString = dateFormat.format(entity.getUpdatedDate());

        return CategoryDTO.builder()
                .id(entity.getId())
                .idparent(entity.getIdparent())
                .name(entity.getName())
                .notes(entity.getNotes())
                .icon(entity.getIcon())
//                .createdDate(createdDateString)
//                .updatedDate(updatedDateString)
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .isactive(entity.getIsactive())
                .build();
    }

    @Override
    public List<Category> toEntity(List<CategoryDTO> dto) {
        List<Category> categoryList = new ArrayList<>();
        dto.forEach(categoryDTO -> {
            categoryList.add(toEntity(categoryDTO));
        });
        return categoryList;
    }

    @Override
    public List<CategoryDTO> toDto(List<Category> entity) {
        List<CategoryDTO> dtos = new ArrayList<>();
        entity.forEach(category -> {
            CategoryDTO categoryDTO = toDto(category);
            dtos.add(categoryDTO);
        });
        return dtos;
    }
}
