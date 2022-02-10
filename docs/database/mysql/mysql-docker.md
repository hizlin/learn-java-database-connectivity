
## MySQL Docker

- Deploying MySQL on Linux with Docker  
  https://dev.mysql.com/doc/refman/8.0/en/linux-installation-docker.html

- Environment Variables  
  https://dev.mysql.com/doc/refman/8.0/en/environment-variables.html

- Server System Variables  
  https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html

- The Binary Log  
  https://dev.mysql.com/doc/refman/8.0/en/binary-log.html

- Replication  
  https://dev.mysql.com/doc/refman/8.0/en/replication.html

- MySQL Group Replication (**MGR**)  
  https://dev.mysql.com/doc/refman/8.0/en/group-replication.html

- InnoDB Configuration  
  https://dev.mysql.com/doc/refman/8.0/en/innodb-configuration.html

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

- FROM: oraclelinux:8-slim
- EXPOSE:
  - 3306:
  - 33060: MySQL X Protocol Port
  - 33061: Group Replication Local Address Port
- 附加配置文件目录: /etc/my.cnf.d (空的目录)
- 默认配置文件(v8.0.28): /etc/my.cnf

#### /etc/my.cnf (默认配置文件内容)
```text
# For advice on how to change settings please see
# https://dev.mysql.com/doc/refman/8.0/en/server-configuration-defaults.html

[mysqld]
#
# Remove leading # and set to the amount of RAM for the most important data cache in MySQL.
# Start at 70% of total RAM for dedicated server, else 10%.
# innodb_buffer_pool_size = 128M
#
# Remove leading # to turn on a very important data integrity option: logging changes to the binary log between backups.
# log_bin
#
# Remove leading # to set options mainly useful for reporting servers.
# The server defaults are faster for transactions and fast SELECTs.
# Adjust sizes as needed, experiment to find the optimal values.
# join_buffer_size = 128M
# sort_buffer_size = 2M
# read_rnd_buffer_size = 2M

# Remove leading # to revert to previous value for default_authentication_plugin, this will increase compatibility with older clients.
# For background, see:
# https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html#sysvar_default_authentication_plugin
# default-authentication-plugin=mysql_native_password

skip-host-cache
skip-name-resolve
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
secure-file-priv=/var/lib/mysql-files
user=mysql

pid-file=/var/run/mysqld/mysqld.pid
```

- 查询初始用户
```text
mysql> select `host`, `user`, `plugin` from mysql.user;

+-----------+------------------+-----------------------+
| host      | user             | plugin                |
+-----------+------------------+-----------------------+
| localhost | healthchecker    | caching_sha2_password |
| localhost | mysql.infoschema | caching_sha2_password |
| localhost | mysql.session    | caching_sha2_password |
| localhost | mysql.sys        | caching_sha2_password |
| localhost | root             | caching_sha2_password |
+-----------+------------------+-----------------------+
```
> 通过环境变量 MYSQL_USER 新增用户 Host=%

### MySQL Docker (Docker Official Images)
> 以下资料来自 v8.0.28

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

- FROM: debian:buster-slim (Debian v10)
- EXPOSE: 3306/33060;
- VOLUME 数据目录: /var/lib/mysql
- Environment 常用环境变量:
  - MYSQL_ROOT_PASSWORD (必需)
  - MYSQL_DATABASE
  - MYSQL_USER
  - MYSQL_PASSWORD

- 查看配置文件权限
```shell
ls -l /etc/mysql/conf.d
```

- 附加配置文件目录: /etc/mysql/conf.d
- 镜像中的配置文件:
  - /etc/mysql/my.cnf (默认)
  - /etc/mysql/my.cnf.fallback
  - /etc/mysql/conf.d/docker.cnf (针对容器优化配置)
  - /etc/mysql/conf.d/mysql.cnf (空的配置)
> 补充说明:
> 服务启动将会合并加载: /etc/mysql/my.cnf & /etc/mysql/conf.d/*.cnf (后者优先)

##### /etc/mysql/my.cnf
```text
#
# The MySQL  Server configuration file.
#
# For explanations see
# https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html

[mysqld]
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL

# Custom config should go here
!includedir /etc/mysql/conf.d/
```

##### /etc/mysql/my.cnf.fallback
```text
#
# The MySQL Community Server configuration file.
#
# For explanations see
# https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html

# * IMPORTANT: Additional settings that can override those from this file!
#   The files must end with '.cnf', otherwise they'll be ignored.
#
!includedir /etc/mysql/conf.d/
```

##### /etc/mysql/conf.d/docker.cnf
```text
[mysqld]
skip-host-cache
skip-name-resolve
```

##### /etc/mysql/conf.d/mysql.cnf
```text
#
# The MySQL  Client configuration file.
#
# For explanations see
# https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html

[mysql]
```

- 使用自定义配置文件的两种方式:  
  (假设宿主的配置文件的路径: ./conf/mysql.cnf)
```yaml
version: "3.9"
services:
  mysql:
    image: mysql:8.0
    volumes:
      # 方式1: 映射 conf.d 目录; 原目录下 docker.cnf & mysql.cnf 文件丢弃;
      - ./conf:/etc/mysql/conf.d
      # 方式2: 映射 配置文件; 替换原目录下 mysql.cnf 文件;
      - ./conf/mysql.cnf:/etc/mysql/conf.d/mysql.cnf
```

- 通过命令修改配置:
```yaml
version: "3.9"
services:
  mysql:
    image: mysql:8.0
    command:
      --character-set-server=utf8mb4
      --collation-server=utf8mb4_unicode_ci
```
- 查询可用命令选项:
```shell
docker run -it --rm mysql:8.0 --verbose --help
```

- 查询初始用户
```text
mysql> select `host`, `user`, `plugin` from mysql.user;

+-----------+------------------+-----------------------+
| host      | user             | plugin                |
+-----------+------------------+-----------------------+
| %         | root             | caching_sha2_password |
| localhost | mysql.infoschema | caching_sha2_password |
| localhost | mysql.session    | caching_sha2_password |
| localhost | mysql.sys        | caching_sha2_password |
| localhost | root             | caching_sha2_password |
+-----------+------------------+-----------------------+
```
> 通过环境变量 MYSQL_USER 新增用户 Host=%
