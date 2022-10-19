package ru.kuzmina.wiskersshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.wiskersshop.exceptions.ResourceNotFoundException;
import ru.kuzmina.wiskersshop.model.Product;
import ru.kuzmina.wiskersshop.model.dtos.Cart;
import ru.kuzmina.wiskersshop.model.dtos.CartItem;

import javax.annotation.PostConstruct;
import java.util.List;

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
                .orElseThrow(() -> new ResourceNotFoundException("Не удалось добавить товар с id: " + productId + " в корзин. Товар не найден."));
        tempCart.add(product);
    }

    public void delete(Long id, Boolean all) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Невозможно удалить товар с id: " + id + " из корзины. Товар не найден."));
        tempCart.delete(product, all);
    }

    public void clear() {
        tempCart.clear();
    }
}
