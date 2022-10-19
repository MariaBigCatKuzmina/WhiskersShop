package ru.kuzmina.wiskersshop.model.dtos;

import lombok.Data;
import ru.kuzmina.wiskersshop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        CartItem item = findById(product.getId());
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
            item.setTotalPrice(item.getTotalPrice() + product.getPrice());
        } else {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        }
        recalculate();
    }

    public void delete(Product product, Boolean all){
        CartItem item = findById(product.getId());
        if (item != null) {
            if (item.getQuantity() > 1 && !all) {
                item.setQuantity(item.getQuantity() - 1);
                item.setTotalPrice(item.getTotalPrice() - product.getPrice());
            } else {
                items.remove(item);
            }
            recalculate();
        }
    }

    private CartItem findById(Long productId) {
        for (CartItem item : items) {
            if (Objects.equals(item.getProductId(), productId)) {
                return item;
            }
        }
        return null;
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
