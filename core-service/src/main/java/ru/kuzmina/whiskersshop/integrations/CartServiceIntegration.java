package ru.kuzmina.whiskersshop.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.paths.get-cart}")
    private String cartUrl;

    @Value("${integration.paths.clear-cart}")
    private String clearCartUrl;

    public CartDto getCurrentCart(){
//        return restTemplate.getForObject("http://localhost:8190/whiskers-petshop-carts/api/v1/cart", CartDto.class);
        return restTemplate.getForObject(cartUrl, CartDto.class);
    }

    public void clear() {
//        restTemplate.execute("http://localhost:8190/whiskers-petshop-carts/api/v1/cart/clear", HttpMethod.GET, null,null);
        restTemplate.execute(clearCartUrl, HttpMethod.GET, null,null);
    }
}
