FROM openjdk:8
EXPOSE 3020
ADD target/docker-like.jar docker-like.jar
ENTRYPOINT ["java","-jar","/docker-like.jar"]