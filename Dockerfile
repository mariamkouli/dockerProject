FROM java:openjdk-8-jdk-alpine
ADD /Educational.jar //
ENTRYPOINT ["java","-jar","/Educational.jar"]
EXPOSE 8080

