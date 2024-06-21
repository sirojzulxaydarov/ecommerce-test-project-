package org.example.online_ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.online_ecommerce.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketProduct {

    private Product product;
    private Integer amount;
}
