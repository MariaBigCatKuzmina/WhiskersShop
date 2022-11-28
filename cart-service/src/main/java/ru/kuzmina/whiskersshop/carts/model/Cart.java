package ru.kuzmina.whiskersshop.carts.model;

import lombok.Data;
import ru.kuzmina.whiskersshop.api.ResourceNotFoundException;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.*;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
        totalPrice = BigDecimal.ZERO;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto product) {
        Optional<CartItem> item = findById(product.getId());
        if (item.isPresent()) {
            item.get().changeQuantity(1);
        } else {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        }
        recalculate();
    }

    public void decrease(Long productId) {
        CartItem item = findById(productId).orElseThrow(() -> new ResourceNotFoundException("товар в корзинене найден id: " + productId));
        if (item.getQuantity() > 1) {
            item.changeQuantity(-1);
            recalculate();
            return;
        }
        remove(productId);
    }

    public void remove(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    private Optional<CartItem> findById(Long productId) {
        for (CartItem item : items) {
            if (Objects.equals(item.getProductId(), productId)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;

        for (CartItem item :
                items) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }
}
