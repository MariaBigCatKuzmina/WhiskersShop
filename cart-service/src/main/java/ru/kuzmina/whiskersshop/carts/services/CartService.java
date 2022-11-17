package ru.kuzmina.whiskersshop.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.api.ResourceNotFoundException;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;
import ru.kuzmina.whiskersshop.carts.integrations.ProductServiceIntegration;
import ru.kuzmina.whiskersshop.carts.model.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart tempCart;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удалось добавить товар с id: " + productId + " в корзину. Товар не найден."));
        tempCart.add(product);
    }

    public void decrease(Long id) {
        ProductDto product = productServiceIntegration.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить товар с id: " + id + " из корзины. Товар не найден."));
        tempCart.decrease(product.getId());
    }

    public void remove(Long id) {
        ProductDto product = productServiceIntegration.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить товар с id: " + id + " из корзины. Товар не найден."));
        tempCart.remove(product.getId());
    }

    public void clear() {
        tempCart.clear();
    }
}
