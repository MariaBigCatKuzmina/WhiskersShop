package ru.kuzmina.whiskersshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.model.Cart;
import ru.kuzmina.whiskersshop.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor

public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCurrent() {
        return cartService.getCurrentCart();
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
