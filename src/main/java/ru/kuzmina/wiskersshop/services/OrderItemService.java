package ru.kuzmina.wiskersshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.wiskersshop.model.OrderItem;
import ru.kuzmina.wiskersshop.repositories.OrderDetailsRepository;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderDetailsRepository.save(orderItem);
    }

    public List<OrderItem> findAllByOrderId(Long id){
        return orderDetailsRepository.findOrderDetailsByOrder_Id(id);

    }
}
