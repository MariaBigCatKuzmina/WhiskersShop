package ru.kuzmina.whiskersshop.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.kuzmina.whiskersshop.api.dtos.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> findById(Long id){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/petshop/api/v1/products/" + id, ProductDto.class));

    }
}
