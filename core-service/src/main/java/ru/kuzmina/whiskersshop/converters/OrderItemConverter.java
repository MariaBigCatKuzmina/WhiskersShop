package ru.kuzmina.whiskersshop.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kuzmina.whiskersshop.api.dtos.OrderItemDto;
import ru.kuzmina.whiskersshop.model.OrderItem;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final ProductConverter productConverter;
    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
         orderItemDto.setProduct(productConverter.entityToDto(orderItem.getProduct()));
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }
    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProduct(productConverter.dtoToEntity(orderItemDto.getProduct()));
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }
}
