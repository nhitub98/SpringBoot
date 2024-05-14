package com.example.demoproject.service;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.mapper.CategoryMapper;
import com.example.demoproject.projection.ICategory;
import com.example.demoproject.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        List<Category> categoryList=categoryRepository.findAll();
        return categoryMapper.toDto(categoryList);
    }

    public CategoryDTO findByIdParent(int id) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy danh mục có id: " + id));
            CategoryDTO categoryDTO = categoryMapper.toDto(category);
            return categoryDTO;
        }

//    public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";
public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";
    public String saveCategory(CategoryDTO categoryDTO, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            // Save the image file to the uploadDir directory
            String originalFileName = file.getOriginalFilename();
            String fileName = StringUtils.cleanPath(originalFileName); // Clean the filename to prevent directory traversal attacks
            File uploadPath = new File(UPLOAD_DIRECTORY);
            File targetFile = new File(uploadPath, fileName);

            try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                fos.write(file.getBytes());
            }

            String iconPath =  fileName;
            categoryDTO.setIcon(iconPath);
        }
        Category category = categoryMapper.toEntity(categoryDTO);
        category.setCreatedDate(LocalDateTime.now());
        category.setUpdatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return "Thêm thành công";
    }

    public String updateCategory(int id, CategoryDTO categoryDTO, MultipartFile file) {
        boolean existsCategory = categoryRepository.existsById(id);

        if (!existsCategory) {
            return "Không có danh mục có id = " + id;
        }

        if (file != null && !file.isEmpty()) {
            // Save the image file to the uploadDir directory
            String originalFileName = file.getOriginalFilename();
            String fileName = StringUtils.cleanPath(originalFileName); // Clean the filename to prevent directory traversal attacks
            File uploadPath = new File(UPLOAD_DIRECTORY);
            File targetFile = new File(uploadPath, fileName);

            try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                fos.write(file.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String iconPath =  fileName;
            categoryDTO.setIcon(iconPath);
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        category.setId(id);
        category.setCreatedDate(LocalDateTime.now());
        category.setUpdatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return "Cập nhật thành công";
    }





    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

}
