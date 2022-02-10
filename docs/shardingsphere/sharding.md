
- 数据分片  
  https://shardingsphere.apache.org/document/5.0.0/cn/dev-manual/sharding/  
  https://shardingsphere.apache.org/document/5.0.0/cn/user-manual/shardingsphere-jdbc/configuration/built-in-algorithm/sharding/

#### 分片算法
- org.apache.shardingsphere.sharding.spi.ShardingAlgorithm
  - StandardShardingAlgorithm<T extends Comparable<?>> (interface)
    - InlineShardingAlgorithm: 行表达式
    - IntervalShardingAlgorithm: 时间范围
    - ModShardingAlgorithm: 取模
    - HashModShardingAlgorithm: 哈希取模
    - AutoIntervalShardingAlgorithm: 可变时间范围
    - AbstractRangeShardingAlgorithm (abstract)
        - VolumeBasedRangeShardingAlgorithm: 分片容量
        - BoundaryBasedRangeShardingAlgorithm: 分片边界
  - ComplexKeysShardingAlgorithm<T extends Comparable<?>> (interface)
    - ComplexInlineShardingAlgorithm: 复合 行表达式
  - HintShardingAlgorithm<T extends Comparable<?>> (interface)
    - HintInlineShardingAlgorithm: Hint 行表达式
  - ClassBasedShardingAlgorithm: 自定义类

#### 服务治理

- 注册中心支持  
  https://shardingsphere.apache.org/document/5.0.0/cn/features/governance/management/registry-center/
  - ZooKeeper (Apache Curator)
  - Etcd (jetcd)

- 可观测性
  - opentelemetry
  - jaeger
  - zipkin

#### PersistRepository

- org.apache.shardingsphere.mode.persist.PersistRepository
  - StandalonePersistRepository
    - org.apache.shardingsphere.mode.repository.standalone.file.FileRepository
  - ClusterPersistRepository
    - org.apache.shardingsphere.mode.repository.cluster.zookeeper.CuratorZookeeperRepository
    - org.apache.shardingsphere.mode.repository.cluster.etcd.EtcdRepository

```html
<!-- https://mvnrepository.com/artifact/org.apache.shardingsphere/shardingsphere-cluster-mode-repository-zookeeper-curator -->
<dependency>
  <groupId>org.apache.shardingsphere</groupId>
  <artifactId>shardingsphere-cluster-mode-repository-zookeeper-curator</artifactId>
  <version>5.0.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.apache.shardingsphere/shardingsphere-cluster-mode-repository-etcd -->
<dependency>
  <groupId>org.apache.shardingsphere</groupId>
  <artifactId>shardingsphere-cluster-mode-repository-etcd</artifactId>
  <version>5.0.0</version>
</dependency>
```

#### spring.shardingsphere.mode.type: Memory/Standalone/Cluster

- Memory 内存模式
```yaml
spring:
  shardingsphere:
    mode:
      type: Memory
```

- Standalone 单机模式
```yaml
spring:
  shardingsphere:
    mode:
      type: Standalone
      repository:
        type: File
        props:
          - path: ${user.home}/.shardingsphere
      overwrite: false
```
> org.apache.shardingsphere.mode.repository.standalone.file.FileRepositoryPropertyKey

- Cluster 集群模式
```yaml
spring:
  shardingsphere:
    mode:
      type: Cluster
      repository:
        type: ZooKeeper
        props:
          - namespace: demo-name
          - server-lists: localhost:2181
          - retry-interval-milliseconds: 500
          - max-retries: 3
          - time-to-live-seconds: 60
          - operation-timeout-milliseconds: 500
          - digest:
      overwrite: false
```
> org.apache.shardingsphere.mode.repository.cluster.ClusterPersistRepositoryConfiguration
> org.apache.shardingsphere.mode.repository.cluster.zookeeper.props.ZookeeperPropertyKey
> org.apache.shardingsphere.mode.repository.cluster.etcd.props.EtcdPropertyKey

####
