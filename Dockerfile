FROM tomcat:8.5.69-jdk11-adoptopenjdk-hotspot

ADD /target/polyclinic-1.0.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]