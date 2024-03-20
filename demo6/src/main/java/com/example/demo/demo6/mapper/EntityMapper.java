package com.example.demo.demo6.mapper;

import java.util.List;

public interface EntityMapper <E,D> {
    E toEntity(D dto); //Map từ DTO sang Entity
    D toDto(E entity);
    List<E> toEntity(List<D> dto);
    List<D> toDto(List<E> entity);
}
