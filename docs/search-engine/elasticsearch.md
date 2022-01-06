
## Elasticsearch

- Configuring  
  https://www.elastic.co/guide/en/elasticsearch/reference/current/settings.html

- Clients  
  https://www.elastic.co/guide/en/elasticsearch/client/index.html
  https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html
  https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-low.html
  https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high.html
```html
<!-- https://mvnrepository.com/artifact/org.elasticsearch.client/elasticsearch-rest-client -->
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-client</artifactId>
    <version>7.16.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.elasticsearch.client/elasticsearch-rest-high-level-client -->
<dependency>
  <groupId>org.elasticsearch.client</groupId>
  <artifactId>elasticsearch-rest-high-level-client</artifactId>
  <version>7.16.2</version>
</dependency>
```


### Docker

- Website  
  https://www.docker.elastic.co/
  
- Source  
  https://github.com/elastic/dockerfiles
  
- Install Elasticsearch with Docker  
  https://www.elastic.co/guide/en/elasticsearch/reference/current/docker.html

- Hub  
  https://hub.docker.com/u/elastic  
  https://hub.docker.com/r/elastic/elasticsearch  
```shell
docker pull elastic/elasticsearch:7.16.2
```

### Docker Official Images

- Hub  
  https://hub.docker.com/_/elasticsearch
```shell
docker pull elasticsearch:7.16.2
```


### Spring Data Elasticsearch

- Website  
  https://spring.io/projects/spring-data-elasticsearch
  
- Documentation  
  https://docs.spring.io/spring-data/elasticsearch/docs/4.2.1/reference/html/
  
- JavaDoc  
  https://docs.spring.io/spring-data/elasticsearch/docs/4.2.1/api/

- Source  
  https://github.com/spring-projects/spring-data-elasticsearch  
  v4.3.0 2021-11-12
```html
<!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-elasticsearch -->
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-elasticsearch</artifactId>
    <version>4.3.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-elasticsearch -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```
> v4.2.1: for Elasticsearch 7.12.1
