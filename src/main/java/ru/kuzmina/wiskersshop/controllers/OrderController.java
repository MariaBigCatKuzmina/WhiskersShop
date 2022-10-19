package ru.kuzmina.wiskersshop.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.wiskersshop.model.Order;
import ru.kuzmina.wiskersshop.model.dtos.Cart;
import ru.kuzmina.wiskersshop.services.OrderService;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public Long addOrder(@RequestBody Cart cart) {
        return orderService.formOrder(cart, "token");
    }

    @GetMapping()
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
//    @PostMapping("/add")
//    public void addOrder(@RequestBody Cart cart, @RequestHeader(name= HttpHeaders.AUTHORIZATION) String token) {
//        orderService.formOrder(cart, token);
//    }
}
