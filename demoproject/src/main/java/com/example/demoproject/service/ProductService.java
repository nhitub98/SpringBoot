package com.example.demoproject.service;

import com.example.demoproject.dto.CategoryDTO;
import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.Product;
import com.example.demoproject.entities.ProductImages;
import com.example.demoproject.mapper.CategoryMapper;
import com.example.demoproject.mapper.ProductMapper;
import com.example.demoproject.repository.CategoryRepository;
import com.example.demoproject.repository.ProductImagesRepository;
import com.example.demoproject.repository.ProductRepository;
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
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductImagesRepository productImagesRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public List<ProductDTO> findAll() {
            List<Product> productList = productRepository.findAll();
            List<Category> categoryList = categoryRepository.findAll();
            return productMapper.toDto(productList);
        }
    public ProductDTO findById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return productMapper.toDto(product);
    }

    public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";

    public String saveProduct(ProductDTO productDTO, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String fileName = StringUtils.cleanPath(originalFileName);
            File uploadPath = new File(UPLOAD_DIRECTORY);
            File targetFile = new File(uploadPath, fileName);
            try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                fos.write(file.getBytes());
            }
            String imagePath = fileName;
            productDTO.setImage(imagePath);
        }
        Category category = categoryMapper.toEntity(productDTO.getCategoryDTO());
        category = categoryRepository.save(category);
        Product product = productMapper.toEntity(productDTO);
//        product.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());
        product.setCategory(category);
        productRepository.save(product);
        return "Thêm thành công";
    }

    public String updateProduct(int id, ProductDTO productDTO, MultipartFile file) {
        boolean existsProduct = productRepository.existsById(id);
        if (!existsProduct) {
            return "Không có Product có id = " + id;
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryDTO().getId());
        if (!optionalCategory.isPresent()) {
            return "Không tìm thấy danh mục có id = " + productDTO.getCategoryDTO().getId();
        }

        Category category = optionalCategory.get();

        if (file != null && !file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String fileName = StringUtils.cleanPath(originalFileName);
            File uploadPath = new File(UPLOAD_DIRECTORY);
            File targetFile = new File(uploadPath, fileName);

            try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                fos.write(file.getBytes());
                String imagePath = fileName;
                productDTO.setImage(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
                return "Có lỗi xảy ra khi tải ảnh lên!";
            }
        }

        Product product = productMapper.toEntity(productDTO);
        product.setId(id);
        product.setCategory(category);
        productRepository.save(product);

        return "Cập nhật thành công";
    }



    public void deleteProduct(int id) {

        productRepository.deleteById(id);
    }

}
