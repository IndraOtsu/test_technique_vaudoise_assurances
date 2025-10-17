FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY vaudoise/gradlew vaudoise/gradlew.bat vaudoise/build.gradle vaudoise/settings.gradle /app/
COPY vaudoise/gradle /app/gradle

RUN chmod +x /app/gradlew || true

COPY vaudoise/src /app/src

RUN ./gradlew --no-daemon clean bootJar -x test

FROM eclipse-temurin:21-jdk
WORKDIR /app

EXPOSE 8080

ENV JAVA_OPTS="" \
    SPRING_PROFILES_ACTIVE="pd"

COPY --from=builder /app/build/libs/*.jar /app/app.jar

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
