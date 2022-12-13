package ru.kuzmina.whiskersshop.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель заказа")
public class OrderItemDto {
    @Schema(description = "Идентификатор элемента заказа", required = true)
    private Long id;
    @Schema(description = "Количество единиц товара в заказе", required = true, example = "1")
    private Integer quantity;
    @Schema(description = "Цена за единицу товара", required = true, example = "178.50")
    private BigDecimal price;
    @Schema(description = "Товар", required = true)
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
