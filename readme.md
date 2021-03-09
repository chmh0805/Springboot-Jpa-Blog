# 스프링부트 블로그 프로젝트

## 의존성
- Spring Boot DevTools
- Lombok
- Spring Data JPA
- MySQL Driver
- Spring Security
- OAuth2 Client
- Spring Web
- Spring-security-taglibs
- tomcat-jasper
- jstl

## DB 설정
```sql
create user 'hyukblog'@'%' identified by 'hyukblog1234';
GRANT ALL PRIVILEGES ON *.* TO 'hyukblog'@'%';
create database hyukblog;
```