package ru.kuzmina.wiskersshop.model;

import lombok.Data;
import ru.kuzmina.wiskersshop.exceptions.ResourceNotFoundException;

import java.util.*;

@Data
public class Cart {
    private List<CartItem> items;
    private Double totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
        totalPrice = 0.0;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) {
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
        totalPrice = 0.0;

        for (CartItem item :
                items) {
            totalPrice += item.getTotalPrice();
        }
    }

    public void clear() {
        items.clear();
        totalPrice = 0.0;
    }
}
