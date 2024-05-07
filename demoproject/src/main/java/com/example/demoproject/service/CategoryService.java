package com.example.demoproject.service;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.mapper.CategoryMapper;
import com.example.demoproject.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> findAllCategories() {
        List<Category> categoryList=categoryRepository.findAllCategory();
        return categoryMapper.toDto(categoryList);
    }

    public CategoryDTO findByIdParent(int idparent) {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByIdParent(idparent);
        return optionalCategory.map(categoryMapper::toDto).orElse(null);
    }


    public String saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        category.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        categoryRepository.save(category);
        return "Thêm thành công";
    }

    public String updateCategory(int id, CategoryDTO categoryDTO) {
        boolean existsCategory = categoryRepository.existsById(id);

        if (!existsCategory) {
            return "Không có danh mục có id = " + id;
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        category.setId(id);
        category.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        category.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
        category.setName(categoryDTO.getName());
        category.setNotes(categoryDTO.getNotes());
        category.setIcon(categoryDTO.getIcon());
        category.setCreatedBy(categoryDTO.getCreatedBy());
        category.setUpdatedBy(categoryDTO.getUpdatedBy());
        category.setIsactive(categoryDTO.getIsactive());
        categoryRepository.save(category);
        return "Cập nhật thành công";
    }


    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }



}
