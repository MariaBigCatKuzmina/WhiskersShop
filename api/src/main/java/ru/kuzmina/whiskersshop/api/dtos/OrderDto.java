package ru.kuzmina.whiskersshop.api.dtos;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private BigDecimal orderPrice;
    private String username;
    private List<OrderItemDto> orderItemsList;

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

    public List<OrderItemDto> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItemDto> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public OrderDto(BigDecimal orderPrice, String username, List<OrderItemDto> orderItemsList) {
        this.orderPrice = orderPrice;
        this.username = username;
        this.orderItemsList = orderItemsList;
    }

    public OrderDto() {
    }
}
