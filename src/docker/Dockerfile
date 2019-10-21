FROM openjdk:8-jre-alpine

ENV JAVA_SECURITY_EGD file:/dev/./urandom
ENV SERVER_PORT 80

EXPOSE $SERVER_PORT

HEALTHCHECK --start-period=30s --timeout=2s CMD wget -q --spider http://localhost/health

WORKDIR /app
COPY *.jar application.jar

VOLUME /app/seed
COPY /seed /app/seed

RUN apk add --no-cache gettext
ENTRYPOINT ["java", "-Duser.timezone=PST", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XshowSettings:vm", "-jar", "/app/application.jar"]
