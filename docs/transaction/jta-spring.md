
## Spring Boot with JTA

- Distributed Transactions with JTA  
  https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/boot-features-jta.html

#### Atomikos

- Home  
  https://www.atomikos.com/Main/TransactionsEssentials

- Source  
  https://github.com/atomikos/transactions-essentials

- v5.x
  - 开源版本: TransactionsEssentials 功能受限?
  - 商业版本: ExtremeTransactions 收费;

- Maven  
  https://mvnrepository.com/artifact/com.atomikos

- Spring Boot Support
```html
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-jta-atomikos -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jta-atomikos</artifactId>
    <version>2.6.6</version>
</dependency>
```
> 依赖: com.atomikos:transactions-jta:4.0.6;

#### Bitronix
> Bitronix Transaction Manager (BTM)

- Source (停止维护)  
  https://github.com/bitronix/btm  

- Spring Boot Support (停止维护)  
  spring-boot-starter-jta-bitronix 维护截至: Spring Boot v2.4.x;

#### Narayana
> JBoss Project

- Home  
  https://narayana.io/

- Source  
  https://github.com/jbosstm/narayana  
  v5.12.6 2022-04-04

- Spring Boot Support (停止维护)  
  spring-boot-starter-jta-narayana 维护截至: Spring Boot v2.0.x;
