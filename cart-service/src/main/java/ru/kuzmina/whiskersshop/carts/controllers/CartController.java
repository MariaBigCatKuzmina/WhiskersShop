package ru.kuzmina.whiskersshop.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;
import ru.kuzmina.whiskersshop.carts.converters.CartConverter;
import ru.kuzmina.whiskersshop.carts.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrent() {
        return cartConverter.cartToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable(name = "id") Long productId) {
        cartService.add(productId);
    }

    @GetMapping("/delete/{id}")
    public void deleteProductFromCart(@PathVariable(name = "id") Long productId) {
        cartService.decrease(productId);
    }

    @GetMapping("/delete/all/{id}")
    public void deleteAllProductsById(@PathVariable Long id){
        cartService.remove(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }

}
