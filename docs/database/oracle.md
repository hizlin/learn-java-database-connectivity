
## Oracle Database

- Versions
  - 21c
  - 19c
  - 18c
  - 12c Release 2 (12.2)
  - 12c Release 1 (12.1)
  - 11g Release 2 (11.2)

- Docs  
  https://docs.oracle.com/en/database/oracle/index.html
  https://docs.oracle.com/en/database/oracle/oracle-database/index.html

### JDBC

- Docs  
  https://www.oracle.com/database/technologies/appdev/jdbc.html

- Maven Central Guide  
  https://www.oracle.com/database/technologies/maven-central-guide.html

- Maven  
  https://mvnrepository.com/artifact/com.oracle.database.jdbc  
```html
<!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc11 -->
<dependency>
  <groupId>com.oracle.database.jdbc</groupId>
  <artifactId>ojdbc11</artifactId>
  <version>21.4.0.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc8 -->
<dependency>
  <groupId>com.oracle.database.jdbc</groupId>
  <artifactId>ojdbc8</artifactId>
  <version>21.4.0.0</version>
</dependency>
```
> ojdbc8: for jdk8+ & oracle 18/19/21;
> ojdbc11: for jdk11+ & oracle 21;


### Docker

- Hub  
  https://hub.docker.com/_/oracle-database-enterprise-edition


