## 멀티스테이지 빌드 DockerFile
mvn clean package  
mvn package dockerfile:build  
```dockerfile
## 멀티스테이지 구성
## 애플리케이션 실행에 필수적이지 않은 것들을 제외 할 수 있음

# stage1
FROM openjdk:11-slim as build

LABEL maintainer="Lee Tae Su <taesulee93@gmail.com>"

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)


# stage2
FROM openjdk:11-slim as build

VOLUME /tmp

ARG DEPENDENCY=/target/depencency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "me.taesu.spcloud.spcloudlicense.SpcloudLicenseApplication"]

```

## 스프링 부트로 도커 이미지 생성하기
mvn clean spring-boot:build-image
docker run -it -p:8080:8080 docker.io/library/spcloud-license:0.0.1-SNAPSHOT

