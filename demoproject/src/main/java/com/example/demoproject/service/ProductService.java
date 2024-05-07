package com.example.demoproject.service;

import com.example.demoproject.dto.ProductDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.Product;
import com.example.demoproject.mapper.ProductMapper;
import com.example.demoproject.repository.CategoryRepository;
import com.example.demoproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productList.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return productDTOList;
    }

    public ProductDTO findById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return productMapper.toDto(product);
    }


    public String saveProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        productRepository.save(product);
        return "Thêm thành công";
    }


    public String updateProduct(int id, ProductDTO productDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getIdcategory().getId());
        Category category = optionalCategory.orElse(null);

        if (category == null) {
            return "Không tìm thấy danh mục có id = " + productDTO.getIdcategory().getId();
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElse(null);

        if (product == null) {
            return "Không tìm thấy sản phẩm có id = " + id;
        }

        product.setIdcategory(category);
        product.setPrice(productDTO.getPrice());
        product.setQuatity(productDTO.getQuatity());
        product.setImage(productDTO.getImage());
        product.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        product.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
        product.setCreatedBy(productDTO.getCreatedBy());
        product.setUpdatedBy(productDTO.getUpdatedBy());
        product.setIsactive(productDTO.getIsactive());

        productRepository.save(product);

        return "Cập nhật thành công";
    }


    public void deleteProduct(int id) {

        productRepository.deleteById(id);
    }

//    @Value("${images_product.upload.dir}")
//    private String UPLOAD;
}
