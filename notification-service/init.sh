#!/bin/bash
MODE=$1
VER=$2

if [ "$VER" == "" ];
then
   VER="1.0"
fi

if [ "$MODE" == "DEV" ];
then
    docker build --no-cache -f Dockerfilelocal -t mirbozorgi/notification-service-dev:$VER .
    _IMG=mirbozorgi/notification-service-dev:$VER _PROFILE=dev docker stack deploy -c docker-compose.yml mirbozorgi-notification-service
else
    docker build --no-cache -t mirbozorgi/notification-service:$VER .
    _IMG=mirbozorgi/notification-service:$VER _PROFILE=prod docker stack deploy -c docker-compose.yml mirbozorgi-notification-service
fi