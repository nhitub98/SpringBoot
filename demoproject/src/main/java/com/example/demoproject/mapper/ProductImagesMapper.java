package com.example.demoproject.mapper;

import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.dto.ProductImagesDTO;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.entities.ProductImages;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductImagesMapper implements EntityMapper<ProductImages, ProductImagesDTO> {
    @Override
    public ProductImages toEntity(ProductImagesDTO dto) {
        return ProductImages.builder()
                .id(dto.getId())
                .name(dto.getName())
                .url(dto.getUrl())
//                .idProduct(ProductImagesMapper.toDto(toEntity.getIdProduct()))
                .build();
    }


    @Override
    public ProductImagesDTO toDto(ProductImages entity) {
        return ProductImagesDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .url(entity.getUrl())
//                .idProduct(ProductImagesMappe.to.getIdProduct())
                .build();
    }

    @Override
    public List<ProductImages> toEntity(List<ProductImagesDTO> dto) {
        List<ProductImages> productImages= new ArrayList<>();
        dto.forEach(productImagesDTO -> productImages.add(toEntity(productImagesDTO)));
        return productImages;
    }

    @Override
    public List<ProductImagesDTO> toDto(List<ProductImages> entity) {
        List<ProductImagesDTO> productImagesDTOList = new ArrayList<>();
        entity.forEach(productImages -> productImagesDTOList.add(toDto(productImages)));
        return productImagesDTOList;
    }
}
