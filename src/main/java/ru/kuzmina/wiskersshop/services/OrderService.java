package ru.kuzmina.wiskersshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.wiskersshop.exceptions.ResourceNotFoundException;
import ru.kuzmina.wiskersshop.model.*;
import ru.kuzmina.wiskersshop.repositories.OrderRepository;
import ru.kuzmina.wiskersshop.utils.JwtTokenUtil;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderItemService orderDetailService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CartService cartService;


    @Transactional
    public Long formOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден: " + principal.getName()));
        Order order = new Order();
        Cart currentCart = cartService.getCurrentCart();
        order.setUser(user);
        order.setOrderPrice(currentCart.getTotalPrice());
        orderRepository.save(order);
        currentCart.getItems()
                .stream()
                .map(cartItem -> new OrderItem(null, cartItem.getQuantity(), cartItem.getProductPrice(),
                        order, productService.findById(cartItem.getProductId()).get(), null, null))
                .forEach(orderDetailService::save);
        return order.getId();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
