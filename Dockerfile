FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /workspace

COPY pom.xml .

RUN mvn --batch-mode dependency:go-offline

COPY src src

RUN mvn --batch-mode package -DskipTests

# Find the generated Spring Boot JAR and give it a fixed name
RUN find /workspace/target -maxdepth 1 -type f -name "*.jar" ! -name "*original*" -exec cp {} /workspace/app.jar \;


FROM eclipse-temurin:17-jre

RUN useradd --system --uid 10001 payments

WORKDIR /app

COPY --from=build /workspace/app.jar /app/app.jar

RUN chown payments:payments /app/app.jar

USER payments

EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "/app/app.jar"]