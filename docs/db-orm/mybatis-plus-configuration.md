
### MyBatis-Plus Spring Boot Configuration

```yaml
# https://baomidou.com/pages/56bac0/
# 配置项的定义:
# mybatis-plus-boot-starter.jar\spring-configuration-metadata.json
# mybatis-plus-boot-starter.jar\additional-spring-configuration-metadata.json
# type: com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties
mybatis-plus:
  # type: String; default: null;
  config-location: null
  # type: String[]; default: ["classpath*:/mapper/**/*.xml"];
  mapper-locations: ["classpath*:/mapper/**/*.xml"]
  # type: String; default: null;
  type-aliases-package: null
  # type: Class<?>; default: null;
  type-aliases-super-type: null
  # type: String; default: null;
  type-handlers-package: null
  # type: String; default: null;
  type-enums-package: null
  # type: boolean; default: false; (Spring Boot Only)
  check-config-location: false
  # type: ExecutorType; default: simple;
  executor-type: simple
  # type: Properties; default: null;
  configuration-properties: null
  ## lazy-initialization: false
  ## mapper-default-scope: null
  # MyBatis 原生配置
  # type: MybatisConfiguration; default: null;
  configuration:
    # type: boolean; default: true
    map-underscore-to-camel-case: true
    # type: Class<? extends TypeHandler>; default: org.apache.ibatis.type.EnumTypeHandler;
    # org.apache.ibatis.type.EnumTypeHandler: 存储枚举名称
    # org.apache.ibatis.type.EnumOrdinalTypeHandler: 存储枚举索引
    # com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler: 枚举实现 IEnum 接口 或者 字段标记 @EnumValue;
    default-enum-type-handler: org.apache.ibatis.type.EnumTypeHandler
    # type: boolean; default: true
    aggressive-lazy-loading: true
    # type: AutoMappingBehavior; default: partial;
    auto-mapping-behavior: partial
    # type: AutoMappingUnknownColumnBehavior; default: NONE
    auto-mapping-unknown-column-behavior: none
    # type: String; default: SESSION
    local-cache-scope: session
    # type: boolean; default: true
    cache-enabled: true
    # type: boolean; default: false;
    call-setters-on-nulls: false
    # type: Class<?>; default: null;
    configuration-factory: null
  # type: GlobalConfig; default: GlobalConfig::new;
  global-config:
    # type: boolean; default: true
    banner: false
    # type: boolean; default: false;
    enable-sql-runner: false
    # type: Class; default: com.baomidou.mybatisplus.core.mapper.Mapper.class;
    super-mapper-class: null
    # type: Set<String>; default: ConcurrentSkipListSet::new;
    mapper-registry-cache: null
    # default: null;
    db-config:
      # type: com.baomidou.mybatisplus.annotation.IdType; default: ASSIGN_ID;
      id-type: assign_id
      # type: String; default: null;
      table-prefix: null
      # type: String; default: null;
      schema: null
      # type: String; default: null;
      column-format: null
      # type: String; default: null;
      property-format: null
      # type: boolean; default: false
      replace-placeholder: false
      # type: String; default: null;
      escape-symbol: null
      # type: boolean; default: true
      table-underline: true
      # type: boolean; default: false
      capital-mode: false
      # type: IKeyGenerator[]; default: null;
      key-generators: null
      # type: String; default: null;
      logic-delete-field: null
      # type: String; default: "1"
      logic-delete-value: "1"
      # type: String; default: "0"
      logic-not-delete-value: "0"
      # type: FieldStrategy; default: NOT_NULL;
      insert-strategy: not_null
      # type: FieldStrategy; default: NOT_NULL;
      update-strategy: not_null
      # type: FieldStrategy; default: NOT_NULL;
      where-strategy: not_null

```