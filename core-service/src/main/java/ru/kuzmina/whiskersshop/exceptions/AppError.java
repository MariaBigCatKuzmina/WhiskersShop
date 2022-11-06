package ru.kuzmina.whiskersshop.exceptions;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppError {
    private int statusCode;
    private String message;


}
