package dev.tools;

import com.baomidou.mybatisplus.generator.ITemplate;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Model 模板配置 (DesignTime)
 */
@Data
public class ModelTemplate implements ITemplate {

    /**
     * Swagger Annotations 注解版本; 有效: 2/3;
     */
    private Integer swaggerVersion = 3;

    private String tenantKeyColumnName;

    /**
     * AddModel 忽略字段
     */
    private Set<String> addModelIgnoreColumns = new HashSet<>();
    /**
     * UpdateModel 忽略字段
     */
    private Set<String> updateModelIgnoreColumns = new HashSet<>();
    /**
     * InfoModel 忽略字段
     */
    private Set<String> infoModelIgnoreColumns = new HashSet<>();
    /**
     * GridModel 忽略字段
     */
    private Set<String> gridModelIgnoreColumns = new HashSet<>();

    @Override
    public @NotNull Map<String, Object> renderData(@NotNull TableInfo tableInfo) {
        return new HashMap<>();
    }
}
