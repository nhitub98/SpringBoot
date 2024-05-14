package com.example.demoproject.service;

import com.example.demoproject.dto.ProductImagesDTO;
import com.example.demoproject.entities.ProductImages;
import com.example.demoproject.mapper.ProductImagesMapper;
import com.example.demoproject.repository.ProductImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductImagesService {
    private final ProductImagesRepository productImagesRepository;
    private final ProductImagesMapper productImagesMapper;

    public List<ProductImagesDTO> findAll() {
        List<ProductImages> productImages = productImagesRepository.findAll();
        return productImagesMapper.toDto(productImages);
    }

    public ProductImagesDTO findById(int id) {
        ProductImages productImages = productImagesRepository.findById(id).orElse(null);
        if (productImages == null) {
            return null;
        }
        return productImagesMapper.toDto(productImages);
    }

    public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";
    public String saveProductImages(ProductImagesDTO productImagesDTO, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String fileName = StringUtils.cleanPath(originalFileName); // Clean the filename to prevent directory traversal attacks
            File uploadPath = new File(UPLOAD_DIRECTORY);
            File targetFile = new File(uploadPath, fileName);
            try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                fos.write(file.getBytes());
            }
            String imagePath = fileName;
            productImagesDTO.setUrl(imagePath);
        }
        ProductImages productImages = productImagesMapper.toEntity(productImagesDTO);
        productImagesRepository.save(productImages);
        return "Thêm thành công";
    }

    public String updateProductImages(int id, ProductImagesDTO productImagesDTO, MultipartFile file) {
        boolean existsProductImages = productImagesRepository.existsById(id);

        if (!existsProductImages) {
            return "Không có Product có id = " + id;
        }

        if (file != null && !file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String fileName = StringUtils.cleanPath(originalFileName);
            File uploadPath = new File(UPLOAD_DIRECTORY);
            File targetFile = new File(uploadPath, fileName);

            try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                fos.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String iconPath = fileName;
            productImagesDTO.setUrl(iconPath);
        }

        ProductImages productImages = productImagesMapper.toEntity(productImagesDTO);
        productImages.setId(id);
        productImagesRepository.save(productImages);
        return "Cập nhật thành công";
    }

    public void deleteProductImages(int id) {
        productImagesRepository.deleteById(id);
    }

    }


