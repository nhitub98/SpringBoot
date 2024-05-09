package com.example.demoproject.controller;


import com.example.demoproject.dto.ProductImagesDTO;
import com.example.demoproject.entities.ProductImages;
import com.example.demoproject.service.ProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/productimages")
public class ProductImagesController {

    @Autowired
    private ProductImagesService productImagesService;


    @GetMapping("/productimages")
    public List<ProductImagesDTO> getAllProductImages() {
        return productImagesService.findAll();
    }

    @GetMapping("/productimages/{id}")
    public ProductImagesDTO getProductImagesById(@PathVariable int id) {
        return productImagesService.findById(id);
    }

    @GetMapping("/productimage/{idproduct}")
    public List<ProductImages> getProductImagesByIdProduct(@PathVariable int idproduct) {
        return productImagesService.findByProductId(idproduct);
    }

    @PostMapping("/add")
    public String saveProductImages(@RequestBody ProductImagesDTO productImagesDTO, MultipartFile file) {
        try {
            String message = productImagesService.saveProductImages(productImagesDTO, file);
            return message;
        } catch (Exception e) {
            // Handle the exception here
            e.printStackTrace(); // Log the exception for debugging (consider a proper logging framework)
            return "Lỗi"; // Return a more informative error message
        }
    }

    @PutMapping("/update/{id}")
    public String updateProductImages(ProductImagesDTO productImagesDTO, @PathVariable int id, @RequestParam("file") MultipartFile file) {
        String message = productImagesService.updateProductImages(id, productImagesDTO, file);
        return message;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductImages(@PathVariable int id) {
        productImagesService.deleteProductImages(id);
        return "Xóa thành công";
    }
}

