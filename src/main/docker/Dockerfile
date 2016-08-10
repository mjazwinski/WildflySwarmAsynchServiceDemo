FROM java:8
VOLUME /tmp
ADD swarm-demo-swarm.jar swarm-app.jar
EXPOSE 8080
RUN sh -c 'touch /swarm-app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/swarm-app.jar"]