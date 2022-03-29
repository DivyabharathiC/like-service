FROM openjdk:8

ADD target/docker-like.jar docker-like.jar

EXPOSE 3020

ENTRYPOINT ["java","-jar","docker-like.jar"]