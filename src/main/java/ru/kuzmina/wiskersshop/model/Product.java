package ru.kuzmina.wiskersshop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 1000 )
    private String description;

    @Column(name="price")
    private Double price;
}
