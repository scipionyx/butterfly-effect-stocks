FROM openjdk:alpine
MAINTAINER Renato Mendes (renato.mendes.1123@gmail.com)
ENV PROJECT_ARTIFACTID butterfly-effect-stocks-backend-distribution
ENV PROJECT_VERSION 0.1.0-SNAPSHOT
ENV SPRING_CLOUD_CONSUL_HOST consul
ADD /butterfly-effect-stocks-backend-bootstrap-0.1.0-SNAPSHOT-exec.jar //
ADD /entrypoint.sh //
RUN chmod +x entrypoint.sh
EXPOSE 8888
ENTRYPOINT ["./entrypoint.sh"]
