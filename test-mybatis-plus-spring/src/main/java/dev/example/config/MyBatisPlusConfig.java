package dev.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import dev.example.commons.snowflake.Snowflake;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;

public class MyBatisPlusConfig {

    // @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector();
    }

    // @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
            }

            @Override
            public void updateFill(MetaObject metaObject) {
            }
        };
    }

    /* 插件
     * https://baomidou.com/pages/2976a3/
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        var interceptor = new MybatisPlusInterceptor();
        // 分页
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 乐观锁 @Version
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    /* 雪花主键生成
     */
    @Bean
    public IdentifierGenerator identifierGenerator() {
        /* 自带实现
         * com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
         * com.baomidou.mybatisplus.core.incrementer.ImadcnIdentifierGenerator;
         */

        var snowflake = Snowflake.builder().build();
        return new IdentifierGenerator() {
            @Override
            public Number nextId(Object entity) {
                return snowflake.next();
            }
        };
    }
}
