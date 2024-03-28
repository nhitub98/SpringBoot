package com.example.demo.demo6.mapper;


import com.example.demo.demo6.dto.ClazzDTO;
import com.example.demo.demo6.entities.Clazz;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClazzMapper implements EntityMapper<Clazz, ClazzDTO> {

    @Override
    public Clazz toEntity(ClazzDTO dto) {
        return Clazz
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public ClazzDTO toDto(Clazz entity) {
        return ClazzDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<Clazz> toEntity(List<ClazzDTO> dto) {
        List<Clazz> classes = new ArrayList<>();
        dto.forEach( clazzDTO -> {
            classes.add(toEntity(clazzDTO));
        });
        return classes;
    }

    @Override
    public List<ClazzDTO> toDto(List<Clazz> entity) {
        List<ClazzDTO> dtos = new ArrayList<>();
        entity.forEach(clazz -> {
            ClazzDTO clazzDTO = toDto(clazz);
            dtos.add(clazzDTO);
        });
        return dtos;
    }
}
