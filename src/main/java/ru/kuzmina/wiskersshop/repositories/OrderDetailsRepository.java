package ru.kuzmina.wiskersshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuzmina.wiskersshop.model.OrderDetails;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    public List<OrderDetails> findOrderDetailsByOrder_Id(Long orderId);
}
