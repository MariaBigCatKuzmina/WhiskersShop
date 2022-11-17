package ru.kuzmina.whiskersshop.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

//    @CreationTimestamp
//    @Column(name = "created_at")
//    private LocalDateTime created_at;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updated_at;

}
