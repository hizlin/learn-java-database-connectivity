package dev.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("product")
public class ProductEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long productId;

    private String name;

    private LocalDateTime createTime;
}
