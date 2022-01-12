package dev.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

// @EnableJdbcAuditing
@EnableJdbcRepositories(basePackages = "dev.example")
@Configuration
public class ProductConfiguration extends AbstractJdbcConfiguration {

    // @Bean
    // public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcOperations operations) {
    //     return new NamedParameterJdbcTemplate(operations);
    // }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
