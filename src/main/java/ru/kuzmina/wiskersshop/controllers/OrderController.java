package ru.kuzmina.wiskersshop.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.wiskersshop.model.Order;
import ru.kuzmina.wiskersshop.services.OrderService;
import ru.kuzmina.wiskersshop.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addOrder(Principal principal) {
        return orderService.formOrder(principal);
    }


}
