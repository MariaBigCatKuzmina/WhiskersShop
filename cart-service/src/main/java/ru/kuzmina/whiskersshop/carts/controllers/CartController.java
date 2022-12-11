package ru.kuzmina.whiskersshop.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;
import ru.kuzmina.whiskersshop.api.dtos.StringResponse;
import ru.kuzmina.whiskersshop.carts.converters.CartConverter;
import ru.kuzmina.whiskersshop.carts.services.CartService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate")
    public StringResponse generateUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }
    @GetMapping("/{uuid}")
    public CartDto getCurrent(@RequestHeader(required = false) String username,
                              @PathVariable(name = "uuid") String uuid) {
        return cartConverter.entityToDto(cartService.getCurrentCart(chooseUuid(username, uuid)));
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addProductToCart(@RequestHeader(required = false) String username,
                                 @PathVariable(name = "uuid") String uuid,
                                 @PathVariable(name = "id") Long productId) {
        cartService.add(chooseUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/delete/{id}")
    public void deleteProductFromCart(@RequestHeader(required = false) String username,
                                      @PathVariable(name = "uuid") String uuid,
                                      @PathVariable(name = "id") Long productId) {
        cartService.decrease(chooseUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/delete/all/{id}")
    public void deleteAllProductsById(@RequestHeader(required = false) String username,
                                      @PathVariable(name = "uuid") String uuid,
                                      @PathVariable Long id){
        cartService.remove(chooseUuid(username, uuid), id);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(@RequestHeader(required = false) String username,
                          @PathVariable(name = "uuid") String uuid) {
        cartService.clear(chooseUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void mergeCarts(@RequestHeader(required = false) String username,
                           @PathVariable(name = "uuid") String uuid) {
        if (username!= null) {
            cartService.mergeCarts(uuid, username);
        }
    }

    private String chooseUuid(String username, String uuid){
        if (username != null){
            return username;
        }
        return uuid;
    }
}
