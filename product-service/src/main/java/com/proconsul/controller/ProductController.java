package com.proconsul.controller;

import com.proconsul.DTO.ProductDTO;
import com.proconsul.entity.Product;
import com.proconsul.model.Message;
import com.proconsul.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary="Salva un prodotto")
    public ResponseEntity<Message> save(@Valid @RequestBody ProductDTO productDTO){
        Message message=productService.save(productDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary="Cerca un prodotto per il suo ID")
    public ProductDTO findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PutMapping("/{id}/decrease")
    public ResponseEntity<ProductDTO> decreaseStock(@PathVariable Long id, @RequestParam Integer quantity) {
        ProductDTO updatedProduct = productService.decreaseStock(id, quantity);
        return ResponseEntity.ok(updatedProduct);
    }
}