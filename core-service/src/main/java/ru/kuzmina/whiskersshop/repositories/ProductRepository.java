package ru.kuzmina.whiskersshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kuzmina.whiskersshop.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = """
                SELECT * FROM products
                WHERE (title like :title or :title IS NULL)
                    AND (price >= :minPrice or :minPrice IS NULL)
                    AND (price <= :maxPrice or :maxPrice IS NULL)
            """, nativeQuery = true)
    List<Product> nsqlGetProductsByTitleLike(String title, BigDecimal minPrice, BigDecimal maxPrice);
}
