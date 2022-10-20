package ru.kuzmina.wiskersshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.wiskersshop.exceptions.ResourceNotFoundException;
import ru.kuzmina.wiskersshop.model.Product;
import ru.kuzmina.wiskersshop.model.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart tempCart;
    private final ProductService productService;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удалось добавить товар с id: " + productId + " в корзину. Товар не найден."));
        tempCart.add(product);
    }

    public void decrease(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить товар с id: " + id + " из корзины. Товар не найден."));
        tempCart.decrease(product.getId());
    }
    public void remove(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить товар с id: " + id + " из корзины. Товар не найден."));
        tempCart.remove(product.getId());
    }

    public void clear() {
        tempCart.clear();
    }
}
