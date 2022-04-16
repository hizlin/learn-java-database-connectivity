
## Java Transaction API (JTA)
> X/Open XA 抽象接口

- JSR 907: Java Transaction API (JTA)  
  https://jcp.org/en/jsr/detail?id=907

### Jakarta Transactions

- Specification  
  https://jakarta.ee/specifications/transactions/

- Project  
  https://projects.eclipse.org/projects/ee4j.jta

- Source  
  https://github.com/eclipse-ee4j/jta-api  
  https://github.com/jakartaee/transactions  
  v2.0.1 2022-03-31  

- Maven
```html
<!-- https://mvnrepository.com/artifact/jakarta.transaction/jakarta.transaction-api -->
<dependency>
    <groupId>jakarta.transaction</groupId>
    <artifactId>jakarta.transaction-api</artifactId>
    <version>2.0.1</version>
</dependency>
```
> v2.0.x:  
> v1.3.x:  

#### v2.0

- v2.0 for Jakarta EE 9  
  https://jakarta.ee/specifications/transactions/2.0/

- Docs  
  https://jakarta.ee/specifications/transactions/2.0/jakarta-transactions-spec-2.0.html

- JavaDoc  
  https://jakarta.ee/specifications/transactions/2.0/apidocs/

#### v1.3

- v1.3 for Jakarta EE 8  
  https://jakarta.ee/specifications/transactions/1.3/

- JavaDoc  
  https://jakarta.ee/specifications/transactions/1.3/apidocs/

### javax.transaction (Legacy)

- Source  
  https://github.com/javaee/javax.transaction

- Maven  
  https://mvnrepository.com/artifact/javax.transaction
```html
<!-- https://mvnrepository.com/artifact/javax.transaction/javax.transaction-api -->
<dependency>
    <groupId>javax.transaction</groupId>
    <artifactId>javax.transaction-api</artifactId>
    <version>1.3</version>
</dependency>
```

### References

- 翻译  
  https://habens.github.io/blog/jta/


## Update: 2022-04-16