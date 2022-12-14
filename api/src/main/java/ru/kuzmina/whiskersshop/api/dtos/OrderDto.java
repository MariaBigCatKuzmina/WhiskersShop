package ru.kuzmina.whiskersshop.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Модель заказа")
public class OrderDto {

    @Schema(description = "Идентификатор заказа", required = true)
    private Long id;
    @Schema(description = "Стоимость заказа", required = true, example = "1500.55")
    private BigDecimal orderPrice;
    @Schema(description = "Имя пользователя сделавшего заказ", required = true, example = "user_Alex")
    private String username;
    @Schema(description = "Список элементов заказа", required = true)
    private List<OrderItemDto> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderDto(BigDecimal orderPrice, String username, List<OrderItemDto> orderItems) {
        this.orderPrice = orderPrice;
        this.username = username;
        this.orderItems = orderItems;
    }

    public OrderDto() { 
    }
}
