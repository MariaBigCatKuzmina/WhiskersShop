package ru.kuzmina.whiskersshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuzmina.whiskersshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
