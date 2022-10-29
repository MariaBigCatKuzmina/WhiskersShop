package ru.kuzmina.wiskersshop;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kuzmina.wiskersshop.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class Cart {
    private List<Product> productList;

    @PostConstruct
    public void Init() {
        productList = new ArrayList<>();
    }
    public void addProductToCart(Product product) {
        if (product != null) {
            productList.add(product);
        }
    }

    public void removeProductFromCart(Product product) {
        if (product != null) {
            productList.remove(product);
        }
    }
}
