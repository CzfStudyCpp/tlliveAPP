FROM openjdk:17
VOLUME /tmp
ADD tl-live-id-generate-provider-1.1.jar app.jar
ENV JAVA_OPTS="\
-server \
-Xmx1g \
-Xms1g \
-Xmn256m"
EXPOSE 8084
ENTRYPOINT java  ${JAVA_OPTS} -jar app.jar