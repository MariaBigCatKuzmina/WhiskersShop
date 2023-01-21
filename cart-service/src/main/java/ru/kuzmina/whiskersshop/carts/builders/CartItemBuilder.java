package ru.kuzmina.whiskersshop.carts.builders;

import ru.kuzmina.whiskersshop.carts.model.CartItem;
import java.math.BigDecimal;

public final class CartItemBuilder {
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private BigDecimal productPrice;
    private BigDecimal totalPrice;

    public CartItemBuilder() {
    }

    public CartItemBuilder withProductId(Long val) {
        productId = val;
        return this;
    }

    public CartItemBuilder withProductTitle(String val) {
        productTitle = val;
        return this;
    }

    public CartItemBuilder withQuantity(Integer val) {
        quantity = val;
        if (productPrice != null) {
            totalPrice = productPrice.multiply(BigDecimal.valueOf(quantity));
        }
        return this;
    }

    public CartItemBuilder withProductPrice(BigDecimal val) {
        productPrice = val;
        if (quantity != null) {
            totalPrice = productPrice.multiply(BigDecimal.valueOf(quantity));
        }
        return this;
    }

    public CartItem build(){
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setProductTitle(productTitle);
        cartItem.setProductPrice(productPrice);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(totalPrice);
        return cartItem;
    }
}
