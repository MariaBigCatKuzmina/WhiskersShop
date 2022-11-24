package ru.kuzmina.whiskersshop.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart() {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(){
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}
