package ru.kuzmina.whiskersshop.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;
import ru.kuzmina.whiskersshop.carts.model.Cart;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;
    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()));
        cartDto.setTotalPrice(cart.getTotalPrice());
        return cartDto;
    }
}
