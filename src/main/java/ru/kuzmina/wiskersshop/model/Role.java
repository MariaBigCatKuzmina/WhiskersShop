package ru.kuzmina.wiskersshop.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


}
