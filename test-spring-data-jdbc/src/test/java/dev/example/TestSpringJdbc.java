package dev.example;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest
public class TestSpringJdbc {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductRepository repository;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SneakyThrows
    @Test
    void test1() {
        Connection connection = dataSource.getConnection();

        RowMapper<ProductEntity> mapper = new RowMapper<>() {
            @Override
            public ProductEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                ProductEntity entity = new ProductEntity();
                String name = resultSet.getString("name");
                entity.setName(name);
                return entity;
            }
        };

        // List<ProductEntity> query = jdbcTemplate.query("SELECT 'tb_product'.'name' AS 'name', 'tb_product'.'price' AS 'price', 'tb_product'.'product_id' AS 'product_id', 'tb_product'.'create_time' AS 'create_time' FROM 'tb_product'", mapper);
        List<ProductEntity> query = jdbcTemplate.query("SELECT * FROM product", mapper);

        var iterable = repository.findAll();

        List<ProductEntity> list = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
