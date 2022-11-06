package ru.kuzmina.whiskersshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.model.OrderItem;
import ru.kuzmina.whiskersshop.repositories.OrderDetailsRepository;

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
