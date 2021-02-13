# Start with OL runtime.
FROM openliberty/open-liberty:kernel-java8-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

USER root

COPY --chown=1001:0 liberty/config/server.xml /config/
COPY --chown=1001:0 target/*.war /config/apps/
COPY --chown=1001:0 liberty/shared/derby-10.14.2.0.jar /liberty/usr/shared/resources/
USER 1001