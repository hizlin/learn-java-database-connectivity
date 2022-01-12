
## Hibernate

- Home  
  https://hibernate.org/

### Hibernate ORM

- Home  
  https://hibernate.org/orm/

- Releases  
  https://hibernate.org/orm/releases/

| Hibernate ORM | 6.0   | 5.6     | 5.5     | 5.4     | 5.3     |
|---------------|-------|---------|---------|---------|---------|
| Java          | 11/17 | 8/11/17 | 8/11/17 | 8/11/17 | 8/11/17 |
| JPA           | 3.0   | 2.2     | 2.2     | 2.2     | 2.2     |
| Jakarta JPA   | N/A   | 3.0     | 3.0     | N/A     | M/A     |

- Source  
  https://github.com/hibernate/hibernate-orm  
  v5.6.3 2021-12-16

- Getting Started  
  https://hibernate.org/orm/documentation/getting-started/

- Maven
```html
<!-- for JPA -->
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.6.3.Final</version>
</dependency>

<!-- for Jakarta JPA -->
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core-jakarta -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core-jakarta</artifactId>
    <version>5.6.3.Final</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.glassfish.jaxb/jaxb-runtime -->
<dependency>
  <groupId>org.glassfish.jaxb</groupId>
  <artifactId>jaxb-runtime</artifactId>
  <version>3.0.2</version>
</dependency>
```


### Hibernate Reactive
> The reactive API for Hibernate ORM.

- Home  
  https://hibernate.org/reactive/

- Releases
  https://hibernate.org/reactive/releases/

| Hibernate Reactive | 1.1   |
|--------------------|-------|
| Java               | 11/17 |
| Hibernate ORM      | 5.6   |
| Vert.x             | 4.2   |

- Source  
  https://github.com/hibernate/hibernate-reactive  
  v1.1.2 2021-12-16

- Maven
```html
<!-- https://mvnrepository.com/artifact/org.hibernate.reactive/hibernate-reactive-core -->
<dependency>
    <groupId>org.hibernate.reactive</groupId>
    <artifactId>hibernate-reactive-core</artifactId>
    <version>1.1.1.Final</version>
</dependency>
```
