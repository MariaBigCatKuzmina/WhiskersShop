package ru.kuzmina.whiskersshop.carts.converters;

import org.springframework.stereotype.Component;
import ru.kuzmina.whiskersshop.api.dtos.CartItemDto;
import ru.kuzmina.whiskersshop.carts.model.CartItem;

@Component
public class CartItemConverter  {
    public CartItemDto entityToDto(CartItem cartItem) {
         CartItemDto cartItemDto = new CartItemDto();
         cartItemDto.setProductId(cartItem.getProductId());
         cartItemDto.setProductTitle(cartItem.getProductTitle());
         cartItemDto.setQuantity(cartItem.getQuantity());
         cartItemDto.setTotalPrice(cartItem.getTotalPrice());
         cartItemDto.setProductPrice(cartItem.getProductPrice());
         return cartItemDto;
    }
}
