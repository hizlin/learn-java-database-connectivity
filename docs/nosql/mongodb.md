
## MongoDB

- Home  
  https://www.mongodb.com/

- SaaS  
  https://www.mongodb.com/atlas

- Source  
  https://github.com/mongodb/mongo

## Docker

- Source  
  https://github.com/docker-library/mongo

- Tags  
  https://github.com/docker-library/docs/tree/master/mongo

- Hub  
  https://hub.docker.com/_/mongo
```shell
# Compressed Size: 240 MB (linux/amd64)
docker pull mongo:focal
docker pull mongo:5.0-focal

# Compressed Size: 400 MB (windows/amd64)
docker pull mongo:nanoserver
docker pull mongo:nanoserver-ltsc2022
docker pull mongo:5.0-nanoserver-ltsc2022

# Compressed Size: 2.32 GB (windows/amd64)
docker pull mongo:windowsservercore
docker pull mongo:windowsservercore-ltsc2022
docker pull mongo:5.0-windowsservercore-ltsc2022
```

## mongo-java-driver

- Home  
  https://mongodb.github.io/mongo-java-driver/

- Source  
  https://github.com/mongodb/mongo-java-driver

- Maven
```html
<!-- https://mvnrepository.com/artifact/org.mongodb/bson -->
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>bson</artifactId>
    <version>4.4.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync -->
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>4.4.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-reactivestreams -->
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-reactivestreams</artifactId>
    <version>4.4.0</version>
</dependency>
```