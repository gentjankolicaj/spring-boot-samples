# spring-boot-samples

Spring boot samples with different tech.

## Project build
| Command description               | Command                              |
|-----------------------------------|--------------------------------------|
| Project clean                     | `mvn clean`                          |
| Project clean compile             | `mvn clean compile`                  |
| Project clean install             | `mvn clean install`                  |
| Project clean pacakge             | `mvn clean pacakge`                  |
| Project clean package (skipTests) | `mvn clean package -DskipTests=true` | 

### Spring Batch:

- [batch + item processor](/batch/batch0)
- [batch + async item processor](/batch/batch1)
- [batch + file reader/writer + jdbc cursor](/batch/batch2)

### Spring Data :
- [data jpa](/data/data-jpa)
- [data jpa + mysql](/data/data-jpa-mysql)
- [data jpa + PostgreSQL](/data/data-jpa-postgresql)
- [data jpa + mongodb](/data/data-mongodb)

### Spring Gateway :
- [(api-gateway + discovery-server) 1](gateway/gateway-01)

### Spring Integration:
- [(rest + integration)](/integration/integration-base)
- [(rest + integration + Kafka)](/integration/integration-kafka)

### Spring JDBC :

- [jdbc template + datasource (HikariCP) + flyway + postgres](/jdbc/jdbc-template-01)
- [jdbc template + multiple datasource's + postgres + mysql](/jdbc/jdbc-template-02)

### Spring Kafka :
- [Kafka (producer & consumer) 1](/kafka/kafka-01)
- [Kafka (producer & consumer) 2](/kafka/kafka-02)

### Spring Security :
- [spring security + data jpa + jwt (io.jsonwebtoken.jjwt) + mysql](/security/jwt-01)
- [spring security + data jpa + jwt(com.auth0.java-jwt) + mysql](/security/jwt-02)

### Spring State Machine

- [spring state machine + REST](/state-machine/base-state-machine)
- [(Tenis) spring state machine + REST](/state-machine/tenis-state-machine)
- [(Different) spring state machine + REST + PERSISTENCE](/state-machine/different-state-machine)

### Spring Test :
- [data jpa + test containers + mysql](/test/test-container-mysql)
- [data jpa + test containers + postgres](/test/test-container-postgres)
- [data jpa + embedded](/test/test-embedded)

### Spring Web :

- [web + data jpa + postgres](/web/web-01)

