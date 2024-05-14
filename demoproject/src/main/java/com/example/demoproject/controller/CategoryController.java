package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String findAllCategories(Model model) {
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
        for (CategoryDTO categoryDTO : categoryDTOList) {
            String baseUrl = "/img/";
            categoryDTO.setIcon(baseUrl + categoryDTO.getIcon());
        }
        model.addAttribute("categories", categoryDTOList);
        return "features/category/all_categories";
    }


    @GetMapping("/add-category")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "features/category/add_category";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute("category") CategoryDTO categoryDTO,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) throws IOException {
        if (categoryDTO != null) {
            categoryDTO.setCreatedDate(LocalDateTime.now());
            categoryDTO.setUpdatedDate(LocalDateTime.now());
            categoryService.saveCategory(categoryDTO, file);
            redirectAttributes.addFlashAttribute("successMessage", "Category added successfully");
            return "redirect:/categories";
        }
        return null;
    }

    @GetMapping("/edit-category/{id}")
    public String showUpdateCategoryForm(@PathVariable int id, Model model) {
        CategoryDTO categoryDTO = categoryService.findByIdParent(id);
        model.addAttribute("category", categoryDTO);
        return "features/category/update_category";
    }
    @PostMapping("/edit-category/{id}")
    public String updateCategory(@PathVariable int id, @ModelAttribute("category") CategoryDTO categoryDTO,@RequestParam("file") MultipartFile file) {
        categoryDTO.setCreatedDate(LocalDateTime.now());
        categoryDTO.setUpdatedDate(LocalDateTime.now());
        categoryService.updateCategory(id, categoryDTO,file);
        return "redirect:/categories";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }


}
