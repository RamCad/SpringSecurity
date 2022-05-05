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

**springsecurity-ssl** - Enable https
  - generate a keystore using keytool
    - keytool -genkey -alias springsecurity-ssl -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore securitykeystore.p12
  - provide parameters related to ssl
    - server.ssl.key-store: Path to the key store that contains the SSL certificate.
    - server.ssl.key-store-password: Password used to access the key store.
    - server.ssl.key-store-type: Type of the key store (PKCS12).
    - server.ssl.key-alias: Alias to identifies the key in the key store.
 
 
Credits - https://www.techgeeknext.com/