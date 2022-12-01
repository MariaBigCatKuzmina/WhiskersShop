package ru.kuzmina.whiskersshop.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.services.OrderService;


@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
@Slf4j

public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addOrder(@RequestHeader String username) {
        return orderService.formOrder(username).getId();
    }


}
