package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    ProductsRepository productsRepository;
    EmbeddedDatabase dataSource;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "IPHONE 20", 555888L),
            new Product(1L, "PIXEL", 15000L),
            new Product(2L, "MacBook Pro, 1gb RAM, 1GHZ, 13inches", 999999999L),
            new Product(3L, "Huawei TotallyNotACloneOfIphone", 2000L),
            new Product(4L, "XIOMI LAPTOP", 15999L),
            new Product(5L, "YANDEX STATION", 20999L),
            new Product(6L, "Sbre station", 10888L)
    );

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "PIXEL", 15000L);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "MacBook Pro, 1gb RAM, 1GHZ, 13inches", 999999999L);
    final Product EXPECTED_SAVED_PRODUCT = new Product(7L, "YANDEX STATION", 20999L);

    @BeforeEach
    void before(){
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("schema.sql")
                .addScript("data.sql").build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void testFindAll() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }

    @Test
    void testFindById() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(1L).get());
    }

    @Test
    void testUpdate() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(2L).get());
    }

    @Test
    void testSave() throws SQLException {
        productsRepository.save(EXPECTED_SAVED_PRODUCT);
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById(7L).get());
    }

    @Test
    void testDelete() throws SQLException {
        productsRepository.delete(0L);
        Assertions.assertThrows(RuntimeException.class, ()->productsRepository.findById(0L));
    }
    @AfterEach
    void after(){
        dataSource.shutdown();
    }
}
