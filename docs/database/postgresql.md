
## PostgreSQL

- Home  
  https://www.postgresql.org/

- Docs  
  https://www.postgresql.org/docs/

- Release Notes  
  https://www.postgresql.org/docs/release/

- Source  
  https://github.com/postgres/postgres

- Versioning Policy  
  https://www.postgresql.org/support/versioning/  

| Version | First Release | Final Release |
|---------|---------------|---------------|
| 14.x    | 2021-09-30    | 2026-11-12    |
| 13.x    | 2020-09-24    | 2025-11-13    |
| 12.x    | 2019-10-03    | 2024-11-14    |
| 11.x    | 2018-10-18    | 2023-11-09    |
| 10.x    | 2017-10-05    | 2022-11-10    |

### PostgreSQL JDBC Driver

- Home  
  https://jdbc.postgresql.org/

- Source  
  https://github.com/pgjdbc/pgjdbc  
  v42.3.1 2021-10-30

- Maven
```html
<!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.3.1</version>
</dependency>
```

### Docker (Docker Official Images)

- Source  
  https://github.com/docker-library/postgres  
  https://github.com/docker-library/official-images/blob/master/library/postgres  

- Hub  
  https://hub.docker.com/_/postgres
```shell
# bullseye
docker pull postgres:latest
docker pull postgres:bullseye
docker pull postgres:14-bullseye

# alpine
docker pull postgres:alpine
docker pull postgres:14-alpine
```
> Tag Format:  
> latest  
> {os} // default: bullseye  
> {major}  
> {major}-{os}  
> {major}.{minor}  
> {major}.{minor}-{os}  

### PostGIS (地理空间数据支持)

- Home  
  https://postgis.net/

- Docs  
  https://postgis.net/docs/

- Source  
  https://github.com/postgis/postgis

- Java  
  https://github.com/postgis/postgis-java  
  v2021.1.0 2021-08-06

- Maven
```html
<!-- https://mvnrepository.com/artifact/net.postgis/postgis-jdbc -->
<dependency>
    <groupId>net.postgis</groupId>
    <artifactId>postgis-jdbc</artifactId>
    <version>2021.1.0</version>
</dependency>
```
