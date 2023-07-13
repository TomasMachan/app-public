ARG BASE_IMAGE=""
FROM $BASE_IMAGE

ARG VERBOSE=false
ARG FEATURE_REPO_URL=""
# ENV OPENJ9_SCC=false

# server.xml 
COPY --chown=1001:0 app/src/main/liberty/config/server.xml /config/
# COPY --chown=1001:0 config/server.xml /config/

# config dropins
# COPY --chown=1001:0 config/configDropins/overrides/*.xml /config/configDropins/overrides/

RUN features.sh

# built app
COPY --chown=1001:0 app/target/greeting.war /config/apps/

# shared resources
COPY --chown=1001:0 resources/ /opt/ol/wlp/usr/shared/resources/

RUN configure.sh
