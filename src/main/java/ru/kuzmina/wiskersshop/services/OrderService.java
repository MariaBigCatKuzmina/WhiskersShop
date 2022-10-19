package ru.kuzmina.wiskersshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuzmina.wiskersshop.model.Order;
import ru.kuzmina.wiskersshop.model.OrderDetails;
import ru.kuzmina.wiskersshop.model.User;
import ru.kuzmina.wiskersshop.model.dtos.Cart;
import ru.kuzmina.wiskersshop.model.dtos.CartItem;
import ru.kuzmina.wiskersshop.repositories.OrderRepository;
import ru.kuzmina.wiskersshop.utils.JwtTokenUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderDetailService orderDetailService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public Long formOrder(Cart cart, String token) {
       // if (token.contains("Bearer ")) {
            List<CartItem> items = cart.getItems();
//            User user = userService.findByUsername(jwtTokenUtil.getUsernameFromToken(token.substring(7))).get();
            User user = userService.findByUsername("user_Ivan").get();
            Order order = orderRepository.save(new Order(LocalDateTime.now(), cart.getTotalPrice(), user));
            for (CartItem item : items) {
                productService.findById(item.getProductId())
                        .ifPresent(product -> orderDetailService.save(new OrderDetails(item.getQuantity(), item.getProductPrice(), order, product)));
            }
//        }
        return order.getId();

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
