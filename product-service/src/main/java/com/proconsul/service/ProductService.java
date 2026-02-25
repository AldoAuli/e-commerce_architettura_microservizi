package com.proconsul.service;

import com.proconsul.DTO.ProductDTO;
import com.proconsul.entity.Product;
import com.proconsul.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductService {

    public Message save(ProductDTO productDTO);

    public ProductDTO findById(Long id);

    public ProductDTO decreaseStock(Long id,Integer quantity);


}
