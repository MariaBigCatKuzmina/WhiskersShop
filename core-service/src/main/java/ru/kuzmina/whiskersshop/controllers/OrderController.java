package ru.kuzmina.whiskersshop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.whiskersshop.api.dtos.OrderDto;
import ru.kuzmina.whiskersshop.services.OrderService;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
@Slf4j
@Tag(name = "Заказы", description = "методы работы с заказами")
public class OrderController {
    private final OrderService orderService;

    @Operation(
            summary = "Создание заказа",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = Long.class))
                    )
            }
    )

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addOrder(@Parameter(description = "Имя авторизованного пользователя", example = "user_Alex")
                         @RequestHeader String username) {
        return orderService.formOrder(username).getId();
    }


    @Operation(
            summary = "Получение страниц с заказми",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping
    public Page<OrderDto> getOrders(@Parameter(name = "Имя авторизованного пользователя", example = "user_Alex") @RequestHeader String username) {
        return orderService.getOrdersForUser(username, 0);
    }

    @Operation(
            summary = "Получение заказа по идентификатору для авторизоанного пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = OrderDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public OrderDto getOrder(@Parameter(description = "Идентификатор заказа", example = "5")
                             @PathVariable(name = "id") Long id,
                             @Parameter(description = "Имя авторизованного пользователя", example = "user_Alex")
                             @RequestHeader String username) {
        return orderService.getOrderByIdForUser(username, id);
    }
}
