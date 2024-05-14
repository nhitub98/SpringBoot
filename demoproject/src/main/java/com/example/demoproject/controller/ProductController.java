package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.mapper.CategoryMapper;
import com.example.demoproject.mapper.ProductMapper;
import com.example.demoproject.repository.CategoryRepository;
import com.example.demoproject.repository.ProductRepository;
import com.example.demoproject.service.CategoryService;
import com.example.demoproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryMapper categoryMapper;

    @GetMapping("/products")
    public String findAllProduct(Model model) {
        List<ProductDTO> productDTOList = productService.findAll();
        for (ProductDTO productDTO : productDTOList) {
            String baseUrl = "/img/";
            productDTO.setImage(baseUrl + productDTO.getImage());
        }
        model.addAttribute("product", productDTOList);
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDTOList);
        return "features/product/all_product";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategories();
        model.addAttribute("categories", categoryDTOList);
        return "features/product/add_product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO,
                             @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (productDTO != null) {
            Category category = categoryMapper.toEntity(productDTO.getCategoryDTO());
            List<Category> allCategories = categoryRepository.findAll();
            model.addAttribute("categories", allCategories);
            productService.saveProduct(productDTO, file);
        }
        return "redirect:/products";
    }


    @GetMapping("/edit-product/{id}")
    public String showUpdateProductForm(@PathVariable int id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("product", productDTO);
        Category category = categoryMapper.toEntity(productDTO.getCategoryDTO());
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        return "features/product/add_product";
    }

    @PostMapping("/edit-product/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
        try {
            Category category = categoryMapper.toEntity(productDTO.getCategoryDTO());

            Optional<Category> existingCategory = categoryRepository.findById(category.getId());
            if (existingCategory.isEmpty()) {
                return "Danh mục không tồn tại";
            }
            category = categoryRepository.save(category);
            productService.updateProduct(id, productDTO, file);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            return "Có lỗi xảy ra khi cập nhật sản phẩm";
        }
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
