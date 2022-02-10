
### Project Structure v5.1.0

#### shardingsphere-agent

- shardingsphere-agent-api
- shardingsphere-agent-bootstrap
- shardingsphere-agent-core
- shardingsphere-agent-distribution
- shardingsphere-agent-plugins
  - shardingsphere-agent-plugin-logging
    - shardingsphere-agent-logging-base
  - shardingsphere-agent-plugin-metrics
    - shardingsphere-agent-metrics-api
    - shardingsphere-agent-metrics-prometheus
  - shardingsphere-agent-plugin-tracing
    - shardingsphere-agent-tracing-opentracing
    - shardingsphere-agent-tracing-opentelemetry
    - shardingsphere-agent-tracing-jaeger
    - shardingsphere-agent-tracing-zipkin
    - shardingsphere-agent-tracing-test

#### shardingsphere-db-protocol

- shardingsphere-db-protocol-core
- shardingsphere-db-protocol-mysql
- shardingsphere-db-protocol-postgresql
- shardingsphere-db-protocol-opengauss

#### shardingsphere-distribution

- shardingsphere-jdbc-distribution
- shardingsphere-proxy-distribution

#### shardingsphere-distsql

- shardingsphere-distsql-parser
- shardingsphere-distsql-statement

#### shardingsphere-features

- shardingsphere-db-discovery
  - shardingsphere-db-discovery-api
  - shardingsphere-db-discovery-core
  - shardingsphere-db-discovery-distsql
    - shardingsphere-db-discovery-distsql-handler
    - shardingsphere-db-discovery-distsql-parser
    - shardingsphere-db-discovery-distsql-statement
  - shardingsphere-db-discovery-provider
    - shardingsphere-db-discovery-mgr
    - shardingsphere-db-discovery-opengauss
  - shardingsphere-db-discovery-spring
    - shardingsphere-db-discovery-spring-namespace
    - shardingsphere-db-discovery-spring-boot-starter
- shardingsphere-encrypt
  - shardingsphere-encrypt-api
  - shardingsphere-encrypt-core
  - shardingsphere-encrypt-distsql
    - shardingsphere-encrypt-distsql-handler
    - shardingsphere-encrypt-distsql-parser
    - shardingsphere-encrypt-distsql-statement
  - shardingsphere-encrypt-spring
    - shardingsphere-encrypt-spring-namespace
    - shardingsphere-encrypt-spring-boot-starter
- shardingsphere-readwrite-splitting (读写分离)
  - shardingsphere-readwrite-splitting-api
  - shardingsphere-readwrite-splitting-core
  - shardingsphere-readwrite-splitting-distsql
    - shardingsphere-readwrite-splitting-distsql-handler
    - shardingsphere-readwrite-splitting-distsql-parser
    - shardingsphere-readwrite-splitting-distsql-statement
  - shardingsphere-readwrite-splitting-spring
    - shardingsphere-readwrite-splitting-spring-namespace
    - shardingsphere-readwrite-splitting-spring-boot-starter
- shardingsphere-shadow
  - shardingsphere-shadow-api
  - shardingsphere-shadow-core
  - shardingsphere-shadow-distsql
    - shardingsphere-shadow-distsql-handler
    - shardingsphere-shadow-distsql-parser
    - shardingsphere-shadow-distsql-statement
  - shardingsphere-shadow-spring
    - shardingsphere-shadow-spring-namespace
    - shardingsphere-shadow-spring-boot-starter
- shardingsphere-sharding (分库分表  )
  - shardingsphere-sharding-api
  - shardingsphere-sharding-core
  - shardingsphere-sharding-distsql
    - shardingsphere-sharding-distsql-handler
    - shardingsphere-sharding-distsql-parser
    - shardingsphere-sharding-distsql-statement
  - shardingsphere-sharding-spring
    - shardingsphere-sharding-spring-namespace
    - shardingsphere-sharding-spring-boot-starter

#### shardingsphere-infra

- shardingsphere-infra-binder
- shardingsphere-infra-common
- shardingsphere-infra-context
- shardingsphere-infra-datetime (时间服务)
  - shardingsphere-infra-datetime-spi
  - shardingsphere-infra-datetime-type
    - shardingsphere-system-datetime
    - shardingsphere-database-datetime
- shardingsphere-infra-executor
- shardingsphere-infra-federation
  - shardingsphere-infra-federation-executor
  - shardingsphere-infra-federation-optimizer
- shardingsphere-infra-merge
- shardingsphere-infra-parser
- shardingsphere-infra-rewrite
- shardingsphere-infra-route

#### shardingsphere-jdbc

- shardingsphere-jdbc-core
- shardingsphere-jdbc-spring
  - shardingsphere-jdbc-core-spring
    - shardingsphere-jdbc-core-spring-namespace
    - shardingsphere-jdbc-core-spring-boot-starter
  - shardingsphere-jdbc-spring-infra
    - shardingsphere-jdbc-spring-namespace-infra
    - shardingsphere-jdbc-spring-boot-starter-infra
  - shardingsphere-jdbc-transaction-spring

#### shardingsphere-kernel

- shardingsphere-authority
  - shardingsphere-authority-api
  - shardingsphere-authority-core
- shardingsphere-data-pipeline
  - shardingsphere-data-pipeline-core
  - shardingsphere-data-pipeline-spi
  - shardingsphere-data-pipeline-dialect
    - shardingsphere-data-pipeline-mysql
    - shardingsphere-data-pipeline-postgresql
    - shardingsphere-data-pipeline-opengauss
- shardingsphere-parser
  - shardingsphere-parser-api
  - shardingsphere-parser-core
  - shardingsphere-parser-spring
    - shardingsphere-parser-spring-namespace
    - shardingsphere-parser-spring-boot-starter
- shardingsphere-schedule
  - shardingsphere-schedule-core
- shardingsphere-single-table
  - shardingsphere-single-table-api
  - shardingsphere-single-table-core
- shardingsphere-traffic
  - shardingsphere-traffic-api
  - shardingsphere-traffic-core
- shardingsphere-transaction
  - shardingsphere-transaction-api
  - shardingsphere-transaction-core
  - shardingsphere-transaction-type (事务支持)
    - shardingsphere-transaction-base
      - shardingsphere-transaction-base-seata-at
    - shardingsphere-transaction-xa
      - shardingsphere-transaction-xa-core
      - shardingsphere-transaction-xa-spi
      - shardingsphere-transaction-xa-provider
        - shardingsphere-transaction-xa-atomikos
        - shardingsphere-transaction-xa-bitronix
        - shardingsphere-transaction-xa-narayana

#### shardingsphere-mode

- shardingsphere-mode-core
- shardingsphere-mode-type
  - shardingsphere-memory-mode
    - shardingsphere-memory-mode-core
  - shardingsphere-standalone-mode
    - shardingsphere-standalone-mode-core
    - shardingsphere-standalone-mode-repository
      - shardingsphere-standalone-mode-repository-api
      - shardingsphere-standalone-mode-repository-provider
        - shardingsphere-standalone-mode-repository-file
  - shardingsphere-cluster-mode
    - shardingsphere-cluster-mode-core
    - shardingsphere-cluster-mode-repository
      - shardingsphere-cluster-mode-repository-api
      - shardingsphere-cluster-mode-repository-provider (注册中心支持)
        - shardingsphere-cluster-mode-repository-zookeeper-curator
        - shardingsphere-cluster-mode-repository-etcd

#### shardingsphere-proxy

- shardingsphere-proxy-bootstrap
- shardingsphere-proxy-frontend
- shardingsphere-proxy-backend
  - shardingsphere-proxy-frontend-core
  - shardingsphere-proxy-frontend-spi
  - shardingsphere-proxy-frontend-mysql
  - shardingsphere-proxy-frontend-postgresql
  - shardingsphere-proxy-frontend-opengauss
  - shardingsphere-proxy-frontend-reactive-core
  - shardingsphere-proxy-frontend-reactive-spi
  - shardingsphere-proxy-frontend-reactive-mysql

#### shardingsphere-spi

#### shardingsphere-sql-parser
- shardingsphere-sql-parser-spi
- shardingsphere-sql-parser-engine
- shardingsphere-sql-parser-statement
- shardingsphere-sql-parser-dialect (解析方言)
  - shardingsphere-sql-parser-mysql
  - shardingsphere-sql-parser-postgresql
  - shardingsphere-sql-parser-opengauss
  - shardingsphere-sql-parser-oracle
  - shardingsphere-sql-parser-sqlserver
  - shardingsphere-sql-parser-sql92
