package ru.kuzmina.wiskersshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuzmina.wiskersshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
