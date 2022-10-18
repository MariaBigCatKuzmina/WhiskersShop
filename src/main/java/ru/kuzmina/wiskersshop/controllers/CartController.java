package ru.kuzmina.wiskersshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.wiskersshop.model.Product;
import ru.kuzmina.wiskersshop.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor

public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<Product> getCartProducts() {
        return cartService.getProductsFromCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable(name = "id") Long productId) {
        cartService.addProductToCart(productId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductFromCart(@PathVariable(name = "id") Long productId) {
        cartService.deleteProductFromCart(productId);
    }
}
