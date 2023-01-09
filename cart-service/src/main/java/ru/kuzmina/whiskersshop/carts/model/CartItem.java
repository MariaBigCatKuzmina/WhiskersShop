package ru.kuzmina.whiskersshop.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private BigDecimal productPrice;
    private BigDecimal totalPrice;

    public void changeQuantity(Integer delta) {
        quantity += delta;
        totalPrice = productPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
