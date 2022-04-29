# SpringSecurity
Spring Security - Implementation of various Authentication types

**main** - Added spring-boot-starter-security
  - login using user and generated password

### Tags

**basic-authentication** - WebSecurityConfig extends WebSecurityConfigurerAdapter 
  - login using custom user and password from application.properties
  
  Basic Authentication uses base64 encoding (not encryption) for generating cryptographic string which contains the information of username and password, which can be easily decoded and not very secure

**credentials-in-db** - WebSecurityConfig
  - digest authentication 
  - login using credentials from db
  
**method-level-security** - @PreAuthorize
  - Annotate configuration class which extends WebSecurityConfigurerAdapter with @EnableGlobalMethodSecurity
 
 
Credits - https://www.techgeeknext.com/