FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update \
&& apt-get install -y git \
&& git config --global user.name "Leonardo Soares" \
&& git config --global user.email "leonardo.soares@sptech.school.com.br" \
&& git clone https://github.com/projeto-fiap/tech-challenge.git

WORKDIR /app/tech-challenge/project

RUN apt-get install -y maven \
&& mvn clean install

EXPOSE 8080

CMD ["java", "-jar", "target/project-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prd"]