package ru.kuzmina.wiskersshop.model;

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

    public void changeQuantity(Integer delta) {
        quantity += delta;
        totalPrice = productPrice * quantity;
    }
}
