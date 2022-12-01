package ru.kuzmina.whiskersshop.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kuzmina.whiskersshop.api.dtos.CartDto;
import ru.kuzmina.whiskersshop.api.dtos.CartItemDto;
import ru.kuzmina.whiskersshop.integrations.CartServiceIntegration;
import ru.kuzmina.whiskersshop.model.Category;
import ru.kuzmina.whiskersshop.model.Order;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.repositories.OrderRepository;
import ru.kuzmina.whiskersshop.services.OrderService;
import ru.kuzmina.whiskersshop.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void createOrderTest() {
        CartDto cartDto = new CartDto();
        List<CartItemDto> cartItems = new ArrayList<>();
        CartItemDto cartItemDto = new CartItemDto("Когтеточка", 2, BigDecimal.valueOf(1500), BigDecimal.valueOf(3000));
        cartItemDto.setProductId(12L);
        cartItems.add(cartItemDto);
        cartDto.setItems(cartItems);
        cartDto.setTotalPrice(BigDecimal.valueOf(3000));

        Category category = new Category();
        category.setId(4L);
        category.setDescription("Приспособления");

        Product product = new Product();
        product.setId(12L);
        product.setTitle("Когтеточка");
        product.setDescription("");
        product.setPrice(BigDecimal.valueOf(1500));
        product.setCategory(category);

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart();
        Mockito.doReturn(Optional.of(product)).when(productService).findById(12L);
        Order order = orderService.formOrder("user_Alex");

        Assertions.assertEquals(BigDecimal.valueOf(3000), order.getOrderPrice());

    }
}
