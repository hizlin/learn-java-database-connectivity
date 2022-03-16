
## Microsoft SQL Server

- Versions
  - 2019 (15.x) (Windows & Linux)
  - 2017 (14.x) (Windows & Linux)
  - 2016 (13.x) (Windows Only)

- Docs  
  https://docs.microsoft.com/zh-cn/sql/?view=sql-server-ver15

### Docker

- Docs  
  https://docs.microsoft.com/zh-cn/sql/linux/quickstart-install-connect-docker?view=sql-server-ver15
  https://docs.microsoft.com/zh-cn/sql/linux/sql-server-linux-docker-container-deployment?view=sql-server-ver15

- DockerHub  
  https://hub.docker.com/_/microsoft-mssql-server
```shell
docker pull mcr.microsoft.com/mssql/server:2019-latest
docker pull mcr.microsoft.com/mssql/server:2017-latest
```
> Base Image: Ubuntu

### Microsoft JDBC Driver for SQL Server

- Source  
  https://github.com/microsoft/mssql-jdbc  
  v10.2.0 2022-02-01  
  v10: 首个支持 jdk17 版本.

- Maven
```html
<!-- https://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc -->
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <version>10.2.0.jre17</version>
</dependency>
```
> for Java 17: 10.2.0.jre17  
> for Java 11: 10.2.0.jre11  
> for Java 8 : 10.2.0.jre8  

- JavaDoc  
  https://javadoc.io/doc/com.microsoft.sqlserver/mssql-jdbc/  

- Docs  
- https://docs.microsoft.com/zh-cn/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15

- Version Compatibility  
  https://docs.microsoft.com/zh-cn/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server-support-matrix?view=sql-server-ver15#sql-version-compatibility

### Azure Data Studio
> for Windows/Linux/macOS

- Source  
  https://github.com/microsoft/azuredatastudio  
  v1.34.0: 2021-12-16;

- Release Notes  
  https://docs.microsoft.com/zh-cn/sql/azure-data-studio/release-notes-azure-data-studio

- Azure Data Studio	vs SSMS  
  https://docs.microsoft.com/zh-cn/sql/azure-data-studio/what-is-azure-data-studio

### SQL Server Management Studio (SSMS)
> Windows Only

- Release Notes  
  https://docs.microsoft.com/zh-cn/sql/ssms/release-notes-ssms  
  v18.10: 2021-10-05;
