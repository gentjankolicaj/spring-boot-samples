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

- [Spring batch + item processor](/batch/batch0)
- [Spring batch + async item processor](/batch/batch1)
- [Spring batch + file reader/writer + jdbc cursor](/batch/batch2)

### Spring Data :

- [Spring data jpa](/data/data-jpa)
- [Spring data jpa + mysql](/data/data-jpa-mysql)
- [Spring data jpa + PostgreSQL](/data/data-jpa-postgresql)
- [Spring  data jpa + mongodb](/data/data-mongodb)

### Spring Gateway :

- [Spring api-gateway + discovery-server 1](gateway/gateway-01)

### Spring Integration:

- [Spring integration + rest](/integration/integration-base)
- [Spring integration + kafka](/integration/integration-kafka)

### Spring JDBC :

- [Spring jdbc template + datasource (HikariCP) + flyway + postgres](/jdbc/jdbc-template-01)
- [Spring jdbc template + multiple datasource's + postgres + mysql](/jdbc/jdbc-template-02)

### Spring Kafka :
- [Kafka (producer & consumer) 1](/kafka/kafka-01)
- [Kafka (producer & consumer) 2](/kafka/kafka-02)

### Spring Security :

- [Spring security + data jpa + jwt (io.jsonwebtoken.jjwt) + mysql](/security/jwt-01)
- [Spring security + data jpa + jwt(com.auth0.java-jwt) + mysql](/security/jwt-02)

### Spring StateMachine

- [Spring statemachine + rest](/state-machine/base-state-machine)
- [(Tenis) spring statemachine + rest](/state-machine/tenis-state-machine)
- [(Different) spring statemachine + rest + persistence](/state-machine/different-state-machine)
- [Reactive spring statemachine + rest](state-machine/reactive-state-machine)

### Spring Test :

- [Spring data jpa + test containers + mysql](/test/test-container-mysql)
- [Spring data jpa + test containers + postgres](/test/test-container-postgres)
- [Spring data jpa + embedded](/test/test-embedded)

### Spring Web :

- [Spring web + data jpa + postgres](/web/web-01)

### Spring WebFlux :

- [Spring webFlux + rest](webflux/webflux-base)
- [Spring webFlux + mongodb + rest](webflux/webflux-mongodb)

