package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    public String saveCategory(@RequestBody CategoryDTO categoryDTO) {
        String message = categoryService.saveCategory(categoryDTO);
        return message;
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
