FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/fxz-server-system.jar fxz-server-system.jar
# RUN apk add --update font-adobe-100dpi ttf-dejavu fontconfig
ENTRYPOINT ["java","-jar","/fxz-server-system.jar", "&"]