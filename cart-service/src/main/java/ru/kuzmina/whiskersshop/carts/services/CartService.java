package ru.kuzmina.whiskersshop.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;
import ru.kuzmina.whiskersshop.carts.integrations.ProductServiceIntegration;
import ru.kuzmina.whiskersshop.carts.model.Cart;
import ru.kuzmina.whiskersshop.carts.model.CartItem;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value(value = "${cart-service.cart-prefix}")
    private String cartPrefix;


    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (Boolean.FALSE.equals(redisTemplate.hasKey(targetUuid))) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        execute(uuid, cart -> {
            Optional<CartItem> cartItem = cart.findById(productId);
            if (cartItem.isPresent()) {
                cart.increase(cartItem.get());
            } else {
                ProductDto product = productServiceIntegration.findById(productId);
                cart.addNew(product);
            }
        });
    }

    public void decrease(String uuid, Long id) {
        execute(uuid, cart -> cart.decrease(id));
    }

    public void remove(String uuid, Long id) {
        execute(uuid, cart -> cart.remove(id));
    }

    public void clear(String uuid) {
        execute(uuid, Cart::clear);
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        Cart currentCart = getCurrentCart(uuid);
        operation.accept(currentCart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, currentCart);
    }

    public void mergeCarts(String uuid, String username) {
        Cart currentCart = getCurrentCart(uuid);
        if (!currentCart.isEmpty()) {
            execute(username, cart -> cart.mergeCart(currentCart));
            execute(uuid, Cart::clear);
        }
    }
}
