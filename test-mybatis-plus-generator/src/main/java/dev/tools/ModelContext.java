package dev.tools;

import com.baomidou.mybatisplus.generator.config.po.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Model 渲染数据 (RunTime)
 */
@Data
public class ModelContext {

    private String namespace;

    private Part add;

    private Part update;

    private Part remove;

    private Part info;

    private Part grid;

    private Part query;

    private TableField key;

    // private List<TableField> keys = new ArrayList<>();

    /**
     * 租户编号
     */
    private TableField tenant;

    private TableField delete;
    // private TableField deleteTime;
    // private TableField deleteUserId;

    private TableField version;

    public boolean isTenantPropertyName(String name) {
        return tenant != null && tenant.getPropertyName().equals(name);
    }

    @Data
    public static class Part {

        /**
         * Package Name
         */
        private String namespace;

        /**
         * Class Name
         */
        private String type;

        private final Set<String> importPackages = new TreeSet<>();

        private final List<TableField> fields = new ArrayList<>();

        public boolean hasPropertyName(String name) {
            for (TableField field : this.fields) {
                if (field.getPropertyName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }

        public void addImportPackage(Class<?> clazz) {
            this.importPackages.add(clazz.getCanonicalName());
        }

        public void addImportPackage(String clazz) {
            this.importPackages.add(clazz);
        }
    }
}
