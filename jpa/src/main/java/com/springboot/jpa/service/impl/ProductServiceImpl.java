package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {     // 조회 메서드
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {   // 저장 메서드
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setUpdateAt(LocalDateTime.now());
        product.setCreateAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setStock(savedProduct.getStock());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setPrice(savedProduct.getPrice());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception{   // 업데이트 메서드(상품정보 중 이름 변경)
        Product changeProduct = productDAO.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changeProduct.getNumber());
        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setStock(changeProduct.getStock());
        productResponseDto.setPrice(changeProduct.getPrice());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception{    // 상품 삭제 메서드
        productDAO.deleteProduct(number);
    }
}