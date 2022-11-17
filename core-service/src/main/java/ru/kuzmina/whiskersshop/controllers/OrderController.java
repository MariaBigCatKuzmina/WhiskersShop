package ru.kuzmina.whiskersshop.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.api.ResourceNotFoundException;
import ru.kuzmina.whiskersshop.model.User;
import ru.kuzmina.whiskersshop.services.OrderService;
import ru.kuzmina.whiskersshop.services.UserService;

import java.security.Principal;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден: " + principal.getName()));
        return orderService.formOrder(user);
    }


}
