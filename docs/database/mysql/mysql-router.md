
### MySQL Router 8.0

- Release Notes  
  https://dev.mysql.com/doc/relnotes/mysql-router/en/  
  https://dev.mysql.com/doc/relnotes/mysql-router/en/news-8-0-x.html

- Docs  
  https://dev.mysql.com/doc/mysql-router/8.0/en/


### MySQL Router Docker

- Source  
  https://github.com/mysql/mysql-docker  
  https://github.com/mysql/mysql-docker/tree/main/mysql-router

- Hub  
  https://hub.docker.com/u/mysql  
  https://hub.docker.com/r/mysql/mysql-router
```shell
docker pull mysql/mysql-router:8.0
```

- Ports:
    - 6446: R/W Connection Port; (读写端口)
    - 6447: R/O Connection Port; (只读端口)
    - 6448: X Protocol R/W Connection Port;
    - 6449: X Protocol R/O Connection Port;
    - 8443: HTTPS REST Interface Port;

