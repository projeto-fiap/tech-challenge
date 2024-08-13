FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update
RUN apt-get install -y git
RUN git config --global user.name "Leonardo Soares"
RUN git config --global user.email "leonardo.soares@sptech.school.com.br"
RUN git clone https://github.com/projeto-fiap/tech-challenge.git

WORKDIR /app/tech-challenge/project

RUN apt-get install -y maven
RUN mvn clean install
RUN ls -la
COPY  target/project-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

CMD ["java", "-jar", "project-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prd"]