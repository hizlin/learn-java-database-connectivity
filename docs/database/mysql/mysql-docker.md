
## MySQL Docker

- Deploying MySQL on Linux with Docker  
  https://dev.mysql.com/doc/refman/8.0/en/linux-installation-docker.html

- Source  
  https://github.com/mysql/mysql-docker  
  https://github.com/mysql/mysql-docker/tree/main/mysql-server

- Hub  
  https://hub.docker.com/u/mysql  
  https://hub.docker.com/r/mysql/mysql-server
```shell
docker pull mysql/mysql-server:8.0         # both
docker pull mysql/mysql-server:8.0-amd64   # only amd64
docker pull mysql/mysql-server:8.0-aarch64 # only arm64
```
> 附加配置文件目录: /etc/my.cnf.d
> 默认配置文件(v8.0.25): /etc/my.cnf

- Ports:
    - 3306:
    - 33060: MySQL X Protocol Port
    - 33061: Group Replication Local Address Port

- The Binary Log  
  https://dev.mysql.com/doc/refman/8.0/en/binary-log.html

- Replication  
  https://dev.mysql.com/doc/refman/8.0/en/replication.html

- MySQL Group Replication (**MGR**)  
  https://dev.mysql.com/doc/refman/8.0/en/group-replication.html

- InnoDB Configuration  
  https://dev.mysql.com/doc/refman/8.0/en/innodb-configuration.html

### MySQL Docker (Docker Official Images)

- Source  
  https://github.com/docker-library/mysql  
  https://github.com/docker-library/official-images/blob/master/library/mysql

- Hub  
  https://hub.docker.com/_/mysql
```shell
docker pull mysql:8.0
```
> data: /var/lib/mysql
> conf: /etc/mysql/conf.d
