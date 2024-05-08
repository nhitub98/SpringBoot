package com.example.demoproject.controller;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.mapper.ProductMapper;
import com.example.demoproject.repository.CategoryRepository;
import com.example.demoproject.repository.ProductRepository;
import com.example.demoproject.service.CategoryService;
import com.example.demoproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
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

    @GetMapping("/products")
    public List<ProductDTO> getAllProduct() {
        return productService.findAll();
    }
    @GetMapping("/product/{id}")
    public ProductDTO getStudentById(@PathVariable int id) {
        return productService.findById(id);
    }

    @PostMapping("/add")
    public String saveProduct(@RequestBody ProductDTO productDTO, MultipartFile file) {
        try {
            String message = productService.saveProduct(productDTO, file);
            return message;
        } catch (Exception e) {
            // Handle the exception here
            e.printStackTrace(); // Log the exception for debugging (consider a proper logging framework)
            return "Lỗi"; // Return a more informative error message
        }
    }

    @PutMapping("/update/{id}")
    String updateProduct(@RequestParam("id") int id, @RequestBody ProductDTO productDTO,@RequestParam("file") MultipartFile file) {
        String message = productService.updateProduct(id, productDTO,file);
        return message;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Xóa thành công";
    }


}
