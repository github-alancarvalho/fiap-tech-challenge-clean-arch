
FROM openjdk:17-alpine AS final


RUN apk add --no-cache maven

WORKDIR /build
COPY . .

RUN mvn clean install -DskipTests


EXPOSE 8080
EXPOSE 9000

# Instale o clienteEntity MySQL
RUN apk add --no-cache mysql-client


ENTRYPOINT ["java", "-jar", "/build/target/fiapfood-0.0.1-SNAPSHOT.jar"]






