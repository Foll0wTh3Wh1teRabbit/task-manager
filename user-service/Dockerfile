FROM amazoncorretto:21-alpine

COPY user-service/build/libs/user-service-1.0.0.jar user-service-1.0.0.jar

ENTRYPOINT ["java","-jar","/user-service-1.0.0.jar"]