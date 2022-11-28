package ru.kuzmina.whiskersshop.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.kuzmina.whiskersshop.model.Category;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.repositories.CategoryRepository;
import ru.kuzmina.whiskersshop.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;


    @Test
    public void getAllProductsTest() throws Exception {
        Category category = new Category();
        category.setId(3L);
        category.setTitle("test category");

        Product product = new Product();
        product.setId(22L);
        product.setTitle("test product");
        product.setPrice(BigDecimal.valueOf(250.99));
        product.setDescription("product for test");
        product.setCategory(category);

        List<Product> allProducts = new ArrayList<>(Arrays.asList(product));

        given(productRepository.findAll()).willReturn(allProducts);

        mockMvc.perform(
                get("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(allProducts.get(0).getTitle())));
    }

    @Test
    public void findProductByIdTest() throws Exception {
        Category category = new Category();
        category.setId(3L);
        category.setTitle("test category");

        Product product = new Product();
        product.setId(22L);
        product.setTitle("test product");
        product.setPrice(BigDecimal.valueOf(250.99));
        product.setDescription("product for test");
        product.setCategory(category);

        List<Product> allProducts = new ArrayList<>(Arrays.asList(product));

        given(productRepository.findById(22L)).willReturn(Optional.ofNullable(allProducts.get(0)));

        mockMvc.perform(
                        get("/api/v1/products/22").contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.title", is(allProducts.get(0).getTitle())));
    }
}
