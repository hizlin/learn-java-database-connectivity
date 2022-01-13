
### 主库操作

- 1.进入 主库
```shell
mysql -uroot -p
```

- 2.在主库上 创建用于 "复制" 账户:
```mysql
CREATE USER 'replica'@'%' IDENTIFIED WITH 'mysql_native_password' BY '123456';
GRANT REPLICATION SLAVE ON *.* TO 'replica'@'%';
FLUSH PRIVILEGES;
```

- 查看 server_id 配置;
```mysql
show variables like 'server_id';

show variables like 'log_bin';
show global variables like 'binlog%';
```

- 查看 binlog;
```mysql
show binary logs;

show master logs;
```

- 显示 主库 状态:
```mysql
show master status;
```

```mysql
show binlog events in 'mysql-bin.000003' limit 30;
```

### 从库操作

- 在从库上 配置 "复制" 参数;
```mysql
CHANGE MASTER TO
MASTER_HOST='mysql-primary',
MASTER_PORT=3306,
MASTER_USER='replica',
MASTER_PASSWORD='123456',
MASTER_LOG_FILE='mysql-bin.000003',
MASTER_LOG_POS=832;
```

- 开启同步:
```mysql
start slave;
```

- 关闭同步:
```mysql
stop slave;
```

- 重置同步设置
```mysql
reset slave;
```

- 显示 从库 状态
```shell
show slave status\G
```

- 从库 远程连接 主库:
```shell
mysql -h mysql-primary -ureplica --get-server-public-key -p
```

- 查看用户权限;
```mysql
SHOW GRANTS FOR 'root'@'localhost';
SHOW GRANTS FOR 'replica'@'%';
```