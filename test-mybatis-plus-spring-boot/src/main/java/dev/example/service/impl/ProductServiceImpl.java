package dev.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dev.example.entity.ProductEntity;
import dev.example.mapper.ProductMapper;
import dev.example.service.ProductService;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {
}
