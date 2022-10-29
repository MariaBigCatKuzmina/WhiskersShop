package ru.kuzmina.wiskersshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuzmina.wiskersshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
