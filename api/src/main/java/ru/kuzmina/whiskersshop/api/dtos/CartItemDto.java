package ru.kuzmina.whiskersshop.api.dtos;

import java.math.BigDecimal;

public class CartItemDto {
    private Long productId;
    private String productTitle;
    private Integer quantity;
    private BigDecimal productPrice;
    private BigDecimal totalPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartItemDto(String productTitle, Integer quantity, BigDecimal productPrice, BigDecimal totalPrice) {
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
    }

    public CartItemDto() {
    }
}
