# Start with a base image containing Java runtime
FROM openjdk:11

# Add Author info
# 임시로 minseo의 이메일 저장
LABEL maintainer="chj2171@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=./build/libs/DongHang-0.0.1-SNAPSHOT.jar
# Add the application's jar to the container
COPY ${JAR_FILE} a504.jar


# Run the jar file
ENTRYPOINT ["java","-jar","/a504.jar"]