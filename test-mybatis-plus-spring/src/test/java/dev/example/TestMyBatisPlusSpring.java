package dev.example;

import dev.example.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestMyBatisPlusSpring {

    @Autowired
    private ProductService productService;

    @Test
    public void test1() {
        var list = productService.list();

        log.info("");
    }
}
