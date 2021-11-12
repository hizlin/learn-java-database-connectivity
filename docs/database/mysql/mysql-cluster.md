
### MySQL NDB Cluster

- Index  
  https://dev.mysql.com/doc/index-cluster.html

### MySQL NDB Cluster 8.0

- Release Notes  
  https://dev.mysql.com/doc/relnotes/mysql-cluster/8.0/en/

- Reference Manual  
  https://dev.mysql.com/doc/refman/8.0/en/mysql-cluster.html

- NDB Cluster vs InnoDB Cluster
  https://dev.mysql.com/doc/refman/8.0/en/mysql-cluster-ndb-innodb-engines.html

### Docker

- Source  
  https://github.com/mysql/mysql-docker  
  https://github.com/mysql/mysql-docker/tree/main/mysql-cluster

- Hub  
  https://hub.docker.com/u/mysql  
  https://hub.docker.com/r/mysql/mysql-cluster
```shell
docker pull mysql/mysql-cluster:8.0
```
- Ports:
  - 3306
  - 33060
  - 2202: default port for Cluster data nodes
  - 1186: default port for Cluster management nodes

- Initial Configuration of NDB Cluster
  https://dev.mysql.com/doc/refman/8.0/en/mysql-cluster-install-configuration.html


### MySQL Cluster Manager 8.0

- Release Notes  
  https://dev.mysql.com/doc/relnotes/mysql-cluster-manager/8.0/en/

- User Manual  
  https://dev.mysql.com/doc/mysql-cluster-manager/8.0/en/


