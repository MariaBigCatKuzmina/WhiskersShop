package ru.kuzmina.whiskersshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;
import ru.kuzmina.whiskersshop.api.dtos.OrderDto;
import ru.kuzmina.whiskersshop.converters.OrderConverter;
import ru.kuzmina.whiskersshop.integrations.CartServiceIntegration;
import ru.kuzmina.whiskersshop.model.Order;
import ru.kuzmina.whiskersshop.model.OrderItem;
import ru.kuzmina.whiskersshop.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderItemService orderDetailService;
    private final CartServiceIntegration cartService;
    private final OrderConverter orderConverter;

    @Transactional
    public Order formOrder(String username) {
        Order order = new Order();
        CartDto currentCart = cartService.getCurrentCart(username);
        order.setUsername(username);
        order.setOrderPrice(currentCart.getTotalPrice());
        List<OrderItem> orderItems = currentCart.getItems()
                .stream()
                .map(cartItem -> new OrderItem(order, productService.findById(cartItem.getProductId()).get(),
                        cartItem.getQuantity(), cartItem.getProductPrice()))
                .toList();
        order.setOrderItemsList(orderItems);
        orderRepository.save(order);
        cartService.clear(username);
        return order;
    }

    public Page<OrderDto> getOrdersForUser(String username, int page) {
        return orderRepository.findByUsername(username, PageRequest.of(page, 5)).map(orderConverter::entityToDto);
    }

    public OrderDto getOrderByIdForUser(String username, Long id) {
         return orderConverter.entityToDto(orderRepository.findOrderByIdAndUsername(id, username));
    }
}
