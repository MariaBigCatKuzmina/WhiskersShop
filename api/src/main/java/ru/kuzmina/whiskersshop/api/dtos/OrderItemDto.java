package ru.kuzmina.whiskersshop.api.dtos;

import java.math.BigDecimal;

public class OrderItemDto {
    private Long id;
    private Integer quantity;
    private BigDecimal price;
    private ProductDto product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public OrderItemDto(Integer quantity, BigDecimal price, ProductDto product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public OrderItemDto() {
    }
}
