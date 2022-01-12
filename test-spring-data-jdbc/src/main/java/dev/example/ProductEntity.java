package dev.example;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("product")
@Data
public class ProductEntity {

    @Id
    @Column("product_id")
    private Long productId;

    @Column("name")
    private String name;

    @Column("create_time")
    @CreatedDate
    private LocalDateTime createTime;
}
