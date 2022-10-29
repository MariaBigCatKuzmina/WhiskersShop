package ru.kuzmina.wiskersshop.exceptions;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppError {
    private int statusCode;
    private String message;


}
