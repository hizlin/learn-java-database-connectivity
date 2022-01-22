package dev.tools;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;

@Slf4j
public class TestMybatisPlusGenerator {

    public static void main(String[] args) {
        try {
            execute();
            log.info("done...");
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    static void execute() {
        var engine = new SharpVelocityTemplateEngine();
        var config1 = config1();
        var config2 = config2();
        var config3 = config3();
        var config4 = config4();
        var config5 = config5();
        var config6 = config6();
        new AutoGenerator(config1)
                .global(config2)
                .packageInfo(config3)
                .template(config4)
                .strategy(config5)
                .injection(config6)
                .execute(engine);
    }

    static DataSourceConfig config1() {
        var url = "";
        var username = "";
        var password = "";
        // 可选配置: +4
        return new DataSourceConfig.Builder(url, username, password)
                // .schema("")
                // .dbQuery(new MySqlQuery())
                // .typeConvert(new MySqlTypeConvert())
                // .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();
    }

    static GlobalConfig config2() {
        var output = "";

        // 全局配置; 可选配置: +8
        return new GlobalConfig.Builder().outputDir(output) // 指定输出目录;
                .fileOverride() // 开启文件覆盖; default: false (禁止);
                .disableOpenDir() // 禁止打开生成目录; default: true (打开);
                .author("CodeGenerator") // 作者;
                .enableSwagger() // 开启 Swagger 注解 (用于 Entity 定义); default: false;
                // .dateType(DateType.TIME_PACK) // 时间类型; default: DateType.TIME_PACK;
                // .commentDate("yyyy-MM-dd") // default: yyyy-MM-dd;
                // .enableKotlin() // 生成 Kotlin 代码文件; default: false;
                .build();
    }

    static PackageConfig config3() {
        var parent = "dev.example";
        var module = "app";
        var paths = Collections.singletonMap(OutputFile.mapperXml, "resources/" + module);

        // 包名配置; 可选配置: +10
        return new PackageConfig.Builder().parent(parent) // default: com.baomidou
                .moduleName(module) // default: ""
                .entity("") // default: entity
                .service("") // default: service
                .serviceImpl("") // default: service.impl
                .mapper("") // default: mapper
                .xml("") // default: mapper.xml // v3.5.0 设置无效?
                .controller("") // default: controller
                .other("") // 输出 自定义文件的 所用包名; default: other;
                .pathInfo(paths) // 重载包的路径配置
                .build();
    }

    static TemplateConfig config4() {
        // 模板配置; 一共六类模板;
        return new TemplateConfig.Builder()
                .disable(TemplateType.CONTROLLER) // 禁用哪些模板;
                .entity("/mybatis-templates/entity.java") // default: /templates/entity.java
                // .entityKt("") // default: /templates/entity.kt
                .service("/mybatis-emplates/service.java") // default: /templates/service.java
                .serviceImpl("/mybatis-templates/serviceImpl.java") // default: /templates/serviceImpl.java
                .mapper("/mybatis-templates/mapper.java") // default: /templates/mapper.java
                .mapperXml("/mybatis-emplates/mapper.xml") // default: /templates/mapper.xml
                .controller("/mybatis-templates/controller.java") // default: /templates/controller.java
                .build();
    }

    static StrategyConfig config5() {
        /* 策略配置
         *
         * # 大写命名; default: false;
         * enableCapitalMode
         *
         * # 跳过视图; default: false;
         * enableSkipView
         *
         * # 启用 Schema; default: false;
         * enableSchema
         *
         * # 禁用 Sql 过滤; default: false;
         * disableSqlFilter
         *
         * # 以下选项 只能配置一项;
         * likeTable(LikeTable)    // 表名模糊匹配 (Sql 过滤)
         * notLikeTable(LikeTable) // 表名模糊排除 (Sql 过滤)
         *
         * # 以下选项 只能配置一项;
         * addInclude(String...) // 表名精确匹配 (内存 过滤)
         * addExclude(String...) // 表名精确排除 (内存 过滤)
         *
         * # 过滤表名前缀; 例如: addTablePrefix("t_"): t_simple => Simple;
         * addTablePrefix(String...)
         *
         * # 过滤表名后缀; 例如: addTableSuffix("_0"): simple_0 => Simple;
         * addTableSuffix(String...)
         *
         * # 过滤字段前缀; 例如: addFieldPrefix("is_"): is_deleted -> deleted;
         * addFieldPrefix(String...)
         *
         * # 过滤字段后缀; 例如: addFieldSuffix("_flag"): deleted_flag -> deleted;
         * addFieldSuffix(String...)
         */
        BaseBuilder builder = new StrategyConfig.Builder();

        /* Entity 可选配置: +20
         *
         * # 名称转换实现
         * nameConvert(INameConvert)
         *
         * # 基类
         * superClass(String)
         * superClass(Class<?>)
         *
         * # 禁用生成 serialVersionUID; default: true;
         * disableSerialVersionUID()
         *
         * # 生成字段常量; default: false;
         * enableColumnConstant()
         *
         * # 开启链式模型; default: false;
         * enableChainModel()
         *
         * # 开启 lombok 模型; default: false;
         * enableLombok()
         *
         * # 移除 is 前缀; default: false;
         * enableRemoveIsPrefix()
         *
         * # 生成字段注解; default: false;
         * enableTableFieldAnnotation()
         *
         * # 开启 ActiveRecord 模型; default: false;
         * enableActiveRecord()
         *
         * # 乐观锁的 字段名
         * versionColumnName(String) // Database
         * versionPropertyName(String) // Entity
         *
         * # 逻辑删除 字段名
         * logicDeleteColumnName(String) // Database
         * logicDeletePropertyName(String) // Entity
         *
         * # 表名 映射命名策略; default: NamingStrategy.underline_to_camel;
         * naming();
         *
         * # 列名 映射命名策略; default: null (按照 naming 执行)
         * columnNaming();
         *
         * # 添加基类公共字段
         * addSuperEntityColumns(String...)
         *
         * # 忽略字段
         * addIgnoreColumns(String...)
         *
         * # 字段填充
         * addTableFills(IFill...)
         * addTableFills(List<IFill>)
         *
         * # 全局主键类型
         * idType(IdType)
         *
         * # 文件名称;
         * convertFileName(entityName -> entityName);
         * formatFileName(%s);
         */
        builder = builder.entityBuilder()
                .enableLombok()
        ;

        /* Mapper 可选配置: +7
         *
         * # 基类; default: com.baomidou.mybatisplus.core.mapper.BaseMapper;
         * superClass(String)
         * superClass(Class<?>)
         *
         * # 开启 @Mapper 注解; default: false;
         * enableMapperAnnotation();
         *
         * # 启用 BaseResultMap 生成; default: false;
         * enableBaseResultMap();
         *
         * # 启用 BaseColumnList 生成; default: false;
         * enableBaseColumnList();
         *
         * # 缓存实现;
         * cache(Class<? extends Cache>)
         *
         * # 类名
         * convertMapperFileName(entityName -> entityName + ConstVal.MAPPER)
         * formatMapperFileName(%Mapper)
         *
         * # Xml 文件名称
         * convertXmlFileName(entityName -> entityName + ConstVal.MAPPER)
         * formatXmlFileName(%sMapper)
         */
        builder = builder.mapperBuilder()
                .enableMapperAnnotation();

        /* Service 可选配置: +4
         *
         * # 服务接口基类 (以下配置等效); default: IService;
         * superServiceClass(com.baomidou.mybatisplus.extension.service.IService.class)
         * superServiceClass(com.baomidou.mybatisplus.extension.service.IService.class.getName())
         *
         * # 服务实现基类 (以下配置等效); default: ServiceImpl;
         * superServiceImplClass(com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.class)
         * superServiceImplClass(com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.class.getName())
         *
         * # 服务接口类名 (以下配置等效); default: I{EntityName}Service;
         * convertServiceFileName(entityName -> "I" + entityName + ConstVal.SERVICE)
         * formatServiceFileName("I%sService")
         *
         * # 服务实现类名 (以下配置等效); default: {EntityName}ServiceImpl;
         * convertServiceImplFileName(entityName -> entityName + ConstVal.SERVICE_IMPL)
         * formatServiceImplFileName("%ServiceImpl")
         */
        builder = builder.serviceBuilder()
                .formatServiceFileName("%sService")
        ;

        /* Controller 可选配置: +4
         *
         * # Controller 基类; default: null;
         * superClass(String)
         * superClass(Class<?>)
         *
         * # Controller 类名; default: {EntityName}Controller
         * convertFileName(entityName -> entityName + ConstVal.CONTROLLER);
         * formatFileName("%sController");
         *
         */
        builder = builder.controllerBuilder()
                // .superClass(BaseController.class)
                // .formatFileName("%sController")
                .enableRestStyle() // 启用 @RestController 注解; default: false;
                .enableHyphenStyle() // @RequestMapping 开启驼峰转连字符; 例如: tableEntity => table-entity; default: false;
        ;

        return builder.build();
    }

    static InjectionConfig config6() {
        var templates = new HashMap<String, String>();
        templates.put("add", "/mybatis-templates/dto/add.java");
        templates.put("update", "/mybatis-templates/dto/update.java");
        templates.put("remove", "/mybatis-templates/dto/remove.java");
        templates.put("info", "/mybatis-templates/dto/info.java");
        templates.put("grid", "/mybatis-templates/dto/grid.java");
        templates.put("query", "/mybatis-templates/dto/query.java");

        var context = new HashMap<String, Object>();

        return new InjectionConfig.Builder()
                .beforeOutputFile((table, map) -> {
                    log.info("表名: {}", table.getEntityName());
                })
                .customMap(context)
                .customFile(templates)
                .build();
    }
}
