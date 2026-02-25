package com.proconsul.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proconsul.DTO.ProductDTO;
import com.proconsul.entity.Product;
import com.proconsul.exception.ResourceNotFoundException;
import com.proconsul.model.Message;
import com.proconsul.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public Message save(ProductDTO productDTO) {
        Product product = objectMapper.convertValue(productDTO,Product.class);
        productRepository.save(product);
        return new Message(LocalDate.now(),"Product successfully saved", HttpStatus.CREATED.value());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product= productRepository.findById(Math.toIntExact(id)).
                orElseThrow(()-> new ResourceNotFoundException("Product with id: "+id+" not found"));
        return objectMapper.convertValue(product,ProductDTO.class);
    }

    @Override
    @Transactional
    public ProductDTO decreaseStock(Long id, Integer quantity) {
        Product product = productRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        product.setQuantity(product.getQuantity() - quantity);

        productRepository.save(product);

        return objectMapper.convertValue(product, ProductDTO.class);
    }
}
