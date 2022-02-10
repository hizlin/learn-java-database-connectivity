
## phpMyAdmin

- Home  
  https://www.phpmyadmin.net/

- Source  
  https://github.com/phpmyadmin/phpmyadmin  
  v5.1.2 2022-01-22

#### Docker (By phpmyadmin)

- Source  
  https://github.com/phpmyadmin/docker  

- Hub  
  https://hub.docker.com/r/phpmyadmin/phpmyadmin  

- Image Variants: {version}-{library}
  - version
    - 忽略 (最新版本)
    - {major}
    - {major}.{minor}
    - {major}.{minor}.{patch}
  - library
    - 忽略 (apache)
    - fpm
    - fpm-alpine
```shell
# 最新版本 (apache)
docker pull phpmyadmin/phpmyadmin:latest

# apache
docker pull phpmyadmin/phpmyadmin:5

# fpm
docker pull phpmyadmin/phpmyadmin:5-fpm

# fpm-alpine
docker pull phpmyadmin/phpmyadmin:5-fpm-alpine
```

#### Docker (By Docker)

- Source  
  https://github.com/docker-library/official-images/blob/master/library/phpmyadmin

- Hub  
  https://hub.docker.com/_/phpmyadmin

- Image Variants: {version}-{library}
  - version
    - 忽略 (最新版本)
    - {major}
    - {major}.{minor}
    - {major}.{minor}.{patch}
  - library
    - apache
    - fpm
    - fpm-alpine
```shell
# 最新版本 (apache)
docker pull phpmyadmin:latest

# apache
docker pull phpmyadmin:5-apache

# fpm
docker pull phpmyadmin:5-fpm

# fpm-alpine
docker pull phpmyadmin:5-fpm-alpine
```
