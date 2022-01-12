
### Java Persistence API

- Specification  
  https://jakarta.ee/specifications/persistence/

- Project  
  https://projects.eclipse.org/projects/ee4j.jpa

- Source  
  https://github.com/eclipse-ee4j/jpa-api  
  v3.0.0 2020-10-27

#### v3.1

- v3.1 for Jakarta EE 10  
  https://jakarta.ee/specifications/persistence/3.1/

#### v3.0

- v3.0 for Jakarta EE 9  
  https://jakarta.ee/specifications/persistence/3.0/

- Docs  
  https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.pdf  
  https://jakarta.ee/specifications/persistence/3.0/jakarta-persistence-spec-3.0.html  

- JavaDoc  
  https://jakarta.ee/specifications/persistence/3.0/apidocs/  
  https://javadoc.io/doc/jakarta.persistence/jakarta.persistence-api/3.0.0/index.html  

#### v2.2

- v2.2 for Jakarta EE 8  
  https://jakarta.ee/specifications/persistence/2.2/

- JavaDoc  
  https://jakarta.ee/specifications/persistence/2.2/apidocs/  
  https://javadoc.io/doc/jakarta.persistence/jakarta.persistence-api/2.2.3/index.html  

#### Maven

```html
<!-- https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api -->
<dependency>
    <groupId>jakarta.persistence</groupId>
    <artifactId>jakarta.persistence-api</artifactId>
    <version>3.0.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api -->
<dependency>
  <groupId>jakarta.persistence</groupId>
  <artifactId>jakarta.persistence-api</artifactId>
  <version>2.2.3</version>
</dependency>
```
> v2.x 版本范围: [2.2.1~2.2.3]  
> v2.x namespace: javax.persistence.*  
> v3.x namespace: jakarta.persistence.*; spi: jakarta.persistence.spi.PersistenceProvider

- Implementations
  - Hibernate
  - EclipseLink
  - Apache OpenJPA


#### Legacy (javax)

- JSR 338: Java Persistence 2.1~2.2  
  https://jcp.org/en/jsr/detail?id=338

- JSR 317: Java Persistence 2.0  
  https://jcp.org/en/jsr/detail?id=317

- Source  
  https://github.com/javaee/jpa-spec  
  v2.2 2017-08-21

- Maven
```html
<!-- https://mvnrepository.com/artifact/javax.persistence/javax.persistence-api -->
<dependency>
    <groupId>javax.persistence</groupId>
    <artifactId>javax.persistence-api</artifactId>
    <version>2.2</version>
</dependency>
```

#### EclipseLink

- Home  
  https://www.eclipse.org/eclipselink/

- JavaDoc  
  https://javadoc.io/doc/org.eclipse.persistence/eclipselink/3.0.2/index.html  
  https://javadoc.io/doc/org.eclipse.persistence/eclipselink/2.7.10/index.html

- Source  
  https://github.com/eclipse-ee4j/eclipselink  
  v2.7.10 2021-12-21  
  v3.0.2 2021-07-16 (for JPA 3.0/Jakarta EE 9)  
  https://github.com/eclipse-ee4j/eclipselink/releases/tag/3.0.0

- Maven
```html
<!-- https://mvnrepository.com/artifact/org.eclipse.persistence/eclipselink -->
<dependency>
  <groupId>org.eclipse.persistence</groupId>
  <artifactId>eclipselink</artifactId>
  <version>3.0.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.eclipse.persistence/eclipselink -->
<dependency>
  <groupId>org.eclipse.persistence</groupId>
  <artifactId>eclipselink</artifactId>
  <version>2.7.10</version>
</dependency>
```
> 命名空间: org.eclipse.persistence;
- v3.0.x
  - 没有依赖;
  - jakarta.persistence.spi.PersistenceProvider: org.eclipse.persistence.jpa.PersistenceProvider;
- v2.7.x
  - 依赖: org.eclipse.persistence:jakarta.persistence (等效 persistence-api);
  - 并且内嵌 persistence-api 代码 (命名空间: javax.persistence);
  - javax.persistence.spi.PersistenceProvider: org.eclipse.persistence.jpa.PersistenceProvider

#### persistence-api (By Eclipse)
> 等效 javax.persistence:javax.persistence-api;

- JavaDoc  
  https://javadoc.io/doc/org.eclipse.persistence/javax.persistence/2.2.1/index.html  
  https://javadoc.io/doc/org.eclipse.persistence/jakarta.persistence/2.2.3/index.html

- Maven  
```html
<!-- https://mvnrepository.com/artifact/org.eclipse.persistence/jakarta.persistence -->
<dependency>
  <groupId>org.eclipse.persistence</groupId>
  <artifactId>jakarta.persistence</artifactId>
  <version>2.2.3</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.eclipse.persistence/javax.persistence -->
<dependency>
    <groupId>org.eclipse.persistence</groupId>
    <artifactId>javax.persistence</artifactId>
    <version>2.2.1</version>
</dependency>
```
> javax 版本范围: [2.0.0~2.2.1]  
> jakarta 版本范围: [2.2.2~2.2.3]  
> 命名空间均为: javax.persistence;

#### Apache OpenJPA

- Home  
  https://openjpa.apache.org/

- Source  
  https://github.com/apache/openjpa  
  v3.2.0: 2021-05-11 (for JPA 2.2)

- Maven  
  https://mvnrepository.com/artifact/org.apache.openjpa
```html
<!-- https://mvnrepository.com/artifact/org.apache.openjpa/openjpa -->
<dependency>
    <groupId>org.apache.openjpa</groupId>
    <artifactId>openjpa</artifactId>
    <version>3.2.0</version>
</dependency>
```

