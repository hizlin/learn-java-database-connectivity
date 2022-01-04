
## Hibernate

- Website  
  https://hibernate.org/

### Hibernate ORM

- Website  
  https://hibernate.org/orm/

- Getting Started  
  https://hibernate.org/orm/documentation/getting-started/

- Source  
  https://github.com/hibernate/hibernate-orm  
  v5.6.3 2021-12-16

- Maven
```html
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.6.3.Final</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core-jakarta -->
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-core-jakarta</artifactId>
  <version>5.6.3.Final</version>
</dependency>
```
> hibernate-core: for JPA 2.2;  
> hibernate-core-jakarta: for Jakarta JPA 3.0;  

- v5.6 Compatibility
  - Java 8/11/17
  - JPA 2.2
  - Jakarta JPA 3.0


### Hibernate Reactive
> The reactive API for Hibernate ORM.

- Website  
  https://hibernate.org/reactive/

- Source  
  https://github.com/hibernate/hibernate-reactive  
  v1.1.1 2021-12-10

- Maven
```html
<!-- https://mvnrepository.com/artifact/org.hibernate.reactive/hibernate-reactive-core -->
<dependency>
    <groupId>org.hibernate.reactive</groupId>
    <artifactId>hibernate-reactive-core</artifactId>
    <version>1.1.1.Final</version>
</dependency>
```

- v1.1 Compatibility
  - Java 11/17
  - Hibernate ORM 5.6+
  - Vert.x 4.2+

