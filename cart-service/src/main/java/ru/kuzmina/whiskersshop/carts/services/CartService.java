package ru.kuzmina.whiskersshop.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;
import ru.kuzmina.whiskersshop.carts.integrations.ProductServiceIntegration;
import ru.kuzmina.whiskersshop.carts.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;

    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String uuid) {
        addCartIfNotExists(uuid);
        return carts.get(uuid);
    }

    private void addCartIfNotExists(String uuid) {
        if (!carts.containsKey(uuid)){
            carts.put(uuid, new Cart());
        }
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.findById(productId);
        addCartIfNotExists(uuid);
        carts.get(uuid).add(product);
    }

    public void decrease(String uuid, Long id) {
        ProductDto product = productServiceIntegration.findById(id);
        carts.get(uuid).decrease(product.getId());
    }

    public void remove(String uuid, Long id) {
        ProductDto product = productServiceIntegration.findById(id);
        carts.get(uuid).remove(product.getId());
    }

    public void clear(String uuid) {
        carts.get(uuid).clear();
    }
}
