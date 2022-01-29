package dev.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.example.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity> {
}
