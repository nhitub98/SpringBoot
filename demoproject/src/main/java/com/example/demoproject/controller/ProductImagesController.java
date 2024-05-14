package com.example.demoproject.controller;


import com.example.demoproject.dto.ProductImagesDTO;
import com.example.demoproject.entities.ProductImages;
import com.example.demoproject.service.ProductImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductImagesController {

    @Autowired
    private ProductImagesService productImagesService;


    @GetMapping("/productimages")
    public String findAllProductImages(Model model){
        List<ProductImagesDTO> productImagesDTOList = productImagesService.findAll();
        model.addAttribute("productImages", productImagesDTOList);
        return "features/productimages/all_product_images";
    }

    @GetMapping("/add-productimages")
    public String showProductImagesForm(Model model) {
        model.addAttribute("productImages", new ProductImagesDTO());
        return "features/add_product_images";
    }

    @PostMapping("/add-productimages")
    public String addProductImages(@ModelAttribute("productImages") ProductImagesDTO productImagesDTO, Model model, @RequestParam("file") MultipartFile file) {
        if (productImagesDTO != null) {
            try {
                productImagesService.saveProductImages(productImagesDTO, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/productimages";
    }

    @GetMapping("/edit-productimages/{id}")
    public String showUpdateProductImagesForm(@PathVariable int id, Model model) {
        ProductImagesDTO productImagesDTO =  productImagesService.findById(id);
        model.addAttribute("productImages", productImagesDTO);
        return "features/formeditproductimages";
    }
    @PostMapping("/edit-productimages/{id}")
    public String updateProductImages(@ModelAttribute("productImages") ProductImagesDTO productImagesDTO, @PathVariable int id,@RequestParam("file") MultipartFile file) {
        productImagesService.updateProductImages(id, productImagesDTO,file);
        return "redirect:/productimages";
    }
    @GetMapping("/delete-productimages/{id}")
    public String deleteProductImages(@PathVariable("id") int id) {
        productImagesService.deleteProductImages(id);
        return "redirect:/productimages";
    }
}

