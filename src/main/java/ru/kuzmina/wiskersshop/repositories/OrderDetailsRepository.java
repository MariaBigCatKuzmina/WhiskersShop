package ru.kuzmina.wiskersshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuzmina.wiskersshop.model.OrderItem;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findOrderDetailsByOrder_Id(Long orderId);
}
