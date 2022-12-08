package ru.kuzmina.whiskersshop.carts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.kuzmina.whiskersshop.api.ResourceNotFoundException;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
        totalPrice = BigDecimal.ZERO;
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

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void mergeCart(Cart cartToMerge) {
        //items.addAll(cartToMerge.getItems());
        cartToMerge.getItems().forEach(mergeItem -> {
            Optional<CartItem> currentCartItem = findById(mergeItem.getProductId());
            if(currentCartItem.isPresent()){
                currentCartItem.get().changeQuantity(mergeItem.getQuantity());
            } else {
                items.add(new CartItem(mergeItem.getProductId(), mergeItem.getProductTitle(), 1,
                        mergeItem.getProductPrice(), mergeItem.getTotalPrice()));
            }
            recalculate();
        });
    }
}
