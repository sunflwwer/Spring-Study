package com.springboot.security.service.impl;

import com.springboot.security.data.dto.ProductDto;
import com.springboot.security.data.dto.ProductResponseDto;
import com.springboot.security.data.entity.Product;
import com.springboot.security.data.repository.ProductRepository;
import com.springboot.security.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        LOGGER.info("[getProduct] input number : {}", number);
        Product product = productRepository.findById(number).get();

        LOGGER.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        LOGGER.info("[getProduct] productDTO : {}", productDto.toString());
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());

        Product saveProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] saveProduct : {}", saveProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(saveProduct.getNumber());
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changeProduct = productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changeProduct.getNumber());
        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setPrice(changeProduct.getPrice());
        productResponseDto.setStock(changeProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productRepository.deleteById(number);
    }
}