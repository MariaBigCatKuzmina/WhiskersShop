package ru.kuzmina.whiskersshop.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.kuzmina.whiskersshop.model.Category;
import ru.kuzmina.whiskersshop.model.Product;
import ru.kuzmina.whiskersshop.repositories.CategoryRepository;
import ru.kuzmina.whiskersshop.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testProductRepository() {
        Category category = new Category();
        category.setTitle("test category");

        entityManager.persistAndFlush(category);

        Product product = new Product();
        product.setTitle("test product");
        product.setPrice(BigDecimal.valueOf(250.99));
        product.setDescription("product for test");
        product.setCategory(category);

        entityManager.persist(product);
        entityManager.flush();

        List<Product> allProducts = productRepository.findAll();

        Assertions.assertEquals(4, allProducts.size());

        allProducts = productRepository.nsqlGetProductsByTitleLike(null, null, BigDecimal.valueOf(1000));
        Assertions.assertEquals(1, allProducts.size());
    }

    @Test
    public void initDbTest(){
        List<Product> allProducts = productRepository.findAll();
        Assertions.assertEquals(3, allProducts.size());
    }
}
