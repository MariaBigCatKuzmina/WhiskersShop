package ru.kuzmina.wiskersshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.wiskersshop.model.OrderDetails;
import ru.kuzmina.wiskersshop.repositories.OrderDetailsRepository;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> findAllByOrderId(Long id){
        return orderDetailsRepository.findOrderDetailsByOrder_Id(id);

    }
}
