package ru.kuzmina.wiskersshop.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderInfoDto {
    private Long orderId;
    private Long userId;
    private String userName;
}
