package com.proconsul.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {


    @NotBlank(message = "name can't be empty")
    @Size(min=1, max=50, message = "The length of the name must be between 1 and 50")
    private String name;

    @NotNull(message = "quantity can't be null")
    private Integer quantity;

    @NotNull(message = "price can't be null")
    private Double price;

}
