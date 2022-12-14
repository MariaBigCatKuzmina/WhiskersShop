package ru.kuzmina.whiskersshop.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель элемента корзины")
public class CartItemDto {
    @Schema(description = "Идентификатор продукта", required = true, example = "1")
    private Long productId;
    @Schema(description = "Название продукта", required = true, example = "Игрушка для кошек \"Мышь\"")
    private String productTitle;
    @Schema(description = "Количество единиц товара в корзине", required = true, example = "5")
    private Integer quantity;
    @Schema(description = "Цена за единицу товара", required = true, example = "178.50")
    private BigDecimal productPrice;
    @Schema(description = "Общая стоимость всех товаров в корзине", required = true, example = "892.50")
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
