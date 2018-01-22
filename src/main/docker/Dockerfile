FROM java:8
VOLUME /tmp
ADD SpringSecurityApp.jar
RUN bash -c 'touch /SpringSecurityApp.jar'
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "SpringSecurityApp.jar"]