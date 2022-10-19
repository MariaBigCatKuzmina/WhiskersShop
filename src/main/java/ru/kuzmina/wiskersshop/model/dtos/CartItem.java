package ru.kuzmina.wiskersshop.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private Double productPrice;
    private Double totalPrice;

}
