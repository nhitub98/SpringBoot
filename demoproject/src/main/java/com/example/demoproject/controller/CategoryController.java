package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/category/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) {
        return categoryService.findByIdParent(id);
    }

    @PostMapping("/add")
    public String saveCategory(@RequestBody CategoryDTO categoryDTO, MultipartFile file) {
        try {
            String message = categoryService.saveCategory(categoryDTO, file);
            return message;
        } catch (Exception e) {
            // Handle the exception here
            e.printStackTrace(); // Log the exception for debugging (consider a proper logging framework)
            return "Error saving category"; // Return a more informative error message
        }
    }

    @PutMapping("/update/{id}")
    public String updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id) {
        String message = categoryService.updateCategory(id, categoryDTO);
        return message;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "Xóa thành công";
    }
}
