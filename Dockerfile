FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update \
&& apt-get install --no-install-recommends -y build-essential nginx \
&& apt-get clean \
&& apt-get install --no-install-recommends -y build-essential git \
&& apt-get clean \
&& git config --global user.name "Leonardo Soares" \
&& git config --global user.email "leonardo.soares@sptech.school.com.br" \
&& git clone https://github.com/projeto-fiap/tech-challenge.git
WORKDIR /app/tech-challenge

RUN apt-get update \
&& apt-get install --no-install-recommends -y build-essential maven \
&& apt-get clean \
&& mvn clean install

EXPOSE 8080
USER nonroot
CMD ["java", "-jar", "target/project-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prd"]