package com.example.demoproject.service;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.mapper.CategoryMapper;
import com.example.demoproject.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public List<CategoryDTO> findAllCategories() {
        List<Category> categoryList=categoryRepository.findAll();
        return categoryMapper.toDto(categoryList);
    }

    public CategoryDTO findByIdParent(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(categoryMapper::toDto).orElse(null);
    }

//    public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";
public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";

    public String saveCategory(CategoryDTO categoryDTO, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            // Save the image file to the uploadDir directory
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File uploadPath = new File(UPLOAD_DIRECTORY);
            File targetFile = new File(uploadPath, fileName);

            try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                fos.write(file.getBytes());
            }


//            String iconPath = "/images/images_category/" + fileName;
            String iconPath = "/img/" + fileName;
            categoryDTO.setIcon(iconPath);
        }


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
