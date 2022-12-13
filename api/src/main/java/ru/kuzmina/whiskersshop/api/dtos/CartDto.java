package ru.kuzmina.whiskersshop.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель корзины")
public class CartDto {
    @Schema(description = "Список элементов корзины", required = true, type = "List<CartItemDto>")
    private List<CartItemDto> items;

    @Schema(description = "Стоимость корзины", required = true, example = "1500.55")
    private BigDecimal totalPrice;

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
