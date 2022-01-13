package dev.example.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.dialect.JdbcH2Dialect;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.H2Dialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

// @EnableJdbcAuditing
@EnableJdbcRepositories(basePackages = "dev.example.product")
@Configuration
public class ProductConfiguration extends AbstractJdbcConfiguration {

    // @Bean
    // public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcOperations operations) {
    //     return new NamedParameterJdbcTemplate(operations);
    // }


    @Override
    public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
        // return super.jdbcDialect(operations);
        var dialect1 = H2Dialect.INSTANCE;
        var dialect2 = JdbcH2Dialect.INSTANCE;
        return  dialect1;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
