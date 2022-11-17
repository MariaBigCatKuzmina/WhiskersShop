package ru.kuzmina.whiskersshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;
import ru.kuzmina.whiskersshop.integrations.CartServiceIntegration;
import ru.kuzmina.whiskersshop.model.Order;
import ru.kuzmina.whiskersshop.model.OrderItem;
import ru.kuzmina.whiskersshop.model.User;
import ru.kuzmina.whiskersshop.repositories.OrderRepository;
import ru.kuzmina.whiskersshop.utils.JwtTokenUtil;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderItemService orderDetailService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CartServiceIntegration cartService;

    @Transactional
    public Long formOrder(User user) {
        Order order = new Order();
        CartDto currentCart = cartService.getCurrentCart();
        order.setUser(user);
        order.setOrderPrice(currentCart.getTotalPrice());
        List<OrderItem> orderItems = currentCart.getItems()
                .stream()
                .map(cartItem -> new OrderItem(order, productService.findById(cartItem.getProductId()).get(),
                        cartItem.getQuantity(), cartItem.getProductPrice()))
                .toList();
        order.setOrderItemsList(orderItems);
        orderRepository.save(order);
         cartService.clear();
        return order.getId();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
