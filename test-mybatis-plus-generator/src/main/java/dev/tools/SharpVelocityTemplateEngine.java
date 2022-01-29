package dev.tools;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

public class SharpVelocityTemplateEngine extends VelocityTemplateEngine {

    @Override
    public @NotNull Map<String, Object> getObjectMap(@NotNull ConfigBuilder config, @NotNull TableInfo tableInfo) {
        var context = super.getObjectMap(config, tableInfo);

        var pc = config.getPackageConfig();

        var output = config.getGlobalConfig().getOutputDir();

        var pathInfo = config.getPathInfo();

        // EntityName 小写开头
        var lowerName = tableInfo.getName();
        var strategy = config.getStrategyConfig();
        if (strategy.getTablePrefix().size() > 0) {
            lowerName = NamingStrategy.removePrefix(lowerName, strategy.getTablePrefix());
        }
        if (strategy.getTableSuffix().size() > 0) {
            lowerName = NamingStrategy.removeSuffix(lowerName, strategy.getTableSuffix());
        }
        lowerName = NamingStrategy.underlineToCamel(lowerName);

        // EntityName 大写开头
        var upperName = NamingStrategy.capitalFirst(lowerName);

        // Model 包的命名空间
        var namespace = pc.joinPackage("model"); // + StringPool.DOT + lowerName;
        // Model 包的目录
        var folder = Paths.get(output, namespace.replaceAll(StringPool.BACK_SLASH + StringPool.DOT, StringPool.BACK_SLASH + File.separator));

        var add = new ModelContext.Part();
        add.setNamespace(namespace);
        add.setType(upperName + "AddForm");

        var update = new ModelContext.Part();
        update.setNamespace(namespace);
        update.setType(upperName + "UpdateForm");

        var remove = new ModelContext.Part();
        remove.setNamespace(namespace);
        remove.setType(upperName + "RemoveForm");

        var query = new ModelContext.Part();
        query.setNamespace(namespace);
        query.setType(upperName + "QueryForm");

        var grid = new ModelContext.Part();
        grid.setNamespace(namespace);
        grid.setType(upperName + "GridDTO");

        var info = new ModelContext.Part();
        info.setNamespace(namespace);
        info.setType(upperName + "InfoDTO");

        var model = new ModelContext();
        model.setNamespace(namespace);
        model.setAdd(add);
        model.setUpdate(update);
        model.setRemove(remove);
        model.setQuery(query);
        model.setGrid(grid);
        model.setInfo(info);

        context.put("model", model);
        context.put("modelFolder", folder.toString());
        context.put("modelService", getLowercaseFirst(tableInfo.getServiceName()));
        return context;
    }

    @Override
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> context) {
        // 取消基类逻辑
        // super.outputCustomFile(customFile, tableInfo, objectMap);

        var folder = (String) context.getOrDefault("modelFolder", null);
        var model = (ModelContext) context.getOrDefault("model", null);
        var modelTemplate = (ModelTemplate) context.getOrDefault("modelTemplate", null);
        context.put("swaggerVersion", modelTemplate.getSwaggerVersion());

        var tenantKey = modelTemplate.getTenantKeyColumnName();
        // Key (仅限一个主键字段)
        for (TableField field : tableInfo.getFields()) {
            if (field.isKeyFlag()) {
                model.setKey(field);
            }
            if (field.isLogicDeleteField()) {
                model.setDelete(field);
            }

            if (tenantKey != null && tenantKey.length() > 0 && tenantKey.equalsIgnoreCase(field.getColumnName())) {
                model.setTenant(field);
            }
        }

        // Add
        var add = customFile.getOrDefault("add.java", null);
        if (add != null && add.length() > 0) {
            for (TableField field : tableInfo.getFields()) {
                if (!modelTemplate.getAddModelIgnoreColumns().contains(field.getColumnName())) {
                    // 排除租户编号
                    if (model.getTenant() == null || !model.getTenant().getColumnName().equalsIgnoreCase(field.getColumnName())) {
                        model.getAdd().getFields().add(field);

                        var p = field.getColumnType().getPkg();
                        if (p != null) {
                            model.getAdd().addImportPackage(p);
                        }
                    }
                }
            }

            var path = Paths.get(folder, model.getAdd().getType() + ConstVal.JAVA_SUFFIX);
            this.outputFile(path.toFile(), context, add);
        }

        // Update
        var update = customFile.getOrDefault("update.java", null);
        if (update != null && update.length() > 0) {
            for (TableField field : tableInfo.getFields()) {
                if (!modelTemplate.getUpdateModelIgnoreColumns().contains(field.getColumnName())) {
                    // 排除租户编号
                    if (model.getTenant() == null || !model.getTenant().getColumnName().equalsIgnoreCase(field.getColumnName())) {
                        model.getUpdate().getFields().add(field);

                        var p = field.getColumnType().getPkg();
                        if (p != null) {
                            model.getUpdate().addImportPackage(p);
                        }
                    }
                }
            }

            var path = Paths.get(folder, model.getUpdate().getType() + ConstVal.JAVA_SUFFIX);
            this.outputFile(path.toFile(), context, update);
        }

        // Remove
        var remove = customFile.getOrDefault("remove.java", null);
        if (remove != null && remove.length() > 0) {
            var path = Paths.get(folder, model.getRemove().getType() + ConstVal.JAVA_SUFFIX);
            this.outputFile(path.toFile(), context, remove);
        }

        // Query
        var query = customFile.getOrDefault("query.java", null);
        if (query != null && query.length() > 0) {
            var path = Paths.get(folder, model.getQuery().getType() + ConstVal.JAVA_SUFFIX);
            this.outputFile(path.toFile(), context, query);
        }

        // Grid
        var grid = customFile.getOrDefault("grid.java", null);
        if (grid != null && grid.length() > 0) {
            for (TableField field : tableInfo.getFields()) {
                if (!modelTemplate.getGridModelIgnoreColumns().contains(field.getColumnName())) {
                    model.getGrid().getFields().add(field);

                    var p = field.getColumnType().getPkg();
                    if (p != null) {
                        model.getGrid().addImportPackage(p);
                    }
                }
            }

            var path = Paths.get(folder, model.getGrid().getType() + ConstVal.JAVA_SUFFIX);
            this.outputFile(path.toFile(), context, grid);
        }

        // Info
        var info = customFile.getOrDefault("info.java", null);
        if (info != null && info.length() > 0) {
            for (TableField field : tableInfo.getFields()) {
                if (!modelTemplate.getInfoModelIgnoreColumns().contains(field.getColumnName())) {
                    model.getInfo().getFields().add(field);

                    var p = field.getColumnType().getPkg();
                    if (p != null) {
                        model.getInfo().addImportPackage(p);
                    }
                }
            }

            var path = Paths.get(folder, model.getInfo().getType() + ConstVal.JAVA_SUFFIX);
            this.outputFile(path.toFile(), context, info);
        }
    }


    protected void outputAddModel(TableInfo tableInfo, Map<String, Object> objectMap) {
    }

    protected void outputUpdateModel(TableInfo tableInfo, Map<String, Object> objectMap) {
    }

    protected void outputRemoveModel(TableInfo tableInfo, Map<String, Object> objectMap) {
    }

    protected void outputQueryModel(TableInfo tableInfo, Map<String, Object> objectMap) {
    }

    protected void outputGridModel(TableInfo tableInfo, Map<String, Object> objectMap) {
    }

    protected void outputInfoModel(TableInfo tableInfo, Map<String, Object> objectMap) {
    }

    // 首个字母小写
    private String getLowercaseFirst(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
