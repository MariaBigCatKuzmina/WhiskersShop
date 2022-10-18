package ru.kuzmina.wiskersshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.wiskersshop.Cart;
import ru.kuzmina.wiskersshop.model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    private final ProductService productService;

    public List<Product> getProductsFromCart() {
        return cart.getProductList();
    }

    public void addProductToCart(Long id) {
        Product product = productService.findById(id).get();
        cart.addProductToCart(product);
    }

    public void deleteProductFromCart(Long id) {
        Product product =productService.findById(id).get();
        cart.removeProductFromCart(product);
    }
}
