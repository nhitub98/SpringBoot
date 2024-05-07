package com.example.demoproject.mapper;

import java.text.ParseException;
import java.util.List;

public interface EntityMapper <E,D> {
    E toEntity(D dto) throws ParseException; //Map từ DTO sang Entity
    D toDto(E entity);
    List<E> toEntity(List<D> dto);
    List<D> toDto(List<E> entity);
}

