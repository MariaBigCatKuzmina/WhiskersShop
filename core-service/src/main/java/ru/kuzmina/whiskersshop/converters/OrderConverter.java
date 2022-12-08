package ru.kuzmina.whiskersshop.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kuzmina.whiskersshop.api.dtos.OrderDto;
import ru.kuzmina.whiskersshop.model.Order;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;
    public OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderItems(order.getOrderItemsList().stream().map(orderItemConverter::entityToDto).toList());
        orderDto.setUsername(order.getUsername());
        orderDto.setOrderPrice(order.getOrderPrice());
        return orderDto;
    }
}
