#!/bin/sh

##
##
##
##

exec java \
	-Dspring.cloud.consul.host=$SPRING_CLOUD_CONSUL_HOST \
	-jar butterfly-effect-stocks-backend-bootstrap-$PROJECT_VERSION-exec.jar