package com.example.demoproject.mapper;

import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.entities.Product;
import com.example.demoproject.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper implements EntityMapper<Product, ProductDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Product toEntity(ProductDTO dto) {
//        Timestamp createdDate = DateUtils.stringToTimestamp(dto.getCreatedDate());
//        Timestamp updatedDate = DateUtils.stringToTimestamp(dto.getUpdatedDate());
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .notes(dto.getNotes())
                .image(dto.getImage())
                .category(categoryMapper.toEntity(dto.getCategoryDTO()))
                .price(dto.getPrice())
                .quatity(dto.getQuatity())
//                .createdDate(createdDate)
//                .updatedDate(updatedDate)
                .createdDate(dto.getCreatedDate())
                .updatedDate(dto.getUpdatedDate())
                .createdBy(dto.getCreatedBy())
                .updatedBy(dto.getUpdatedBy())
                .isactive(dto.getIsactive())
                .build();
    }

    @Override
    public ProductDTO toDto(Product entity) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String createdDate = dateFormat.format(entity.getCreatedDate());
//        String updatedDate = dateFormat.format(entity.getUpdatedDate());
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .notes(entity.getNotes())
                .image(entity.getImage())
                .price(entity.getPrice())
                .quatity(entity.getQuatity())
//                .createdDate(createdDate)
//                .updatedDate(updatedDate)
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .categoryDTO(categoryMapper.toDto(entity.getCategory()))
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .isactive(entity.getIsactive())
                .build();
    }

    @Override
    public List<Product> toEntity(List<ProductDTO> dtoList) {
        List<Product> productList = new ArrayList<>();
        dtoList.forEach(productDTO -> {
            productList.add(toEntity(productDTO));
        });
        return productList;
    }

    @Override
    public List<ProductDTO> toDto(List<Product> entityList) {
        List<ProductDTO> dtoList = new ArrayList<>();
        entityList.forEach(product -> {
            ProductDTO productDTO = toDto(product);
            dtoList.add(productDTO);
        });
        return dtoList;
    }
}
