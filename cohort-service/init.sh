#!/bin/bash
MODE=$1
VER=$2

if [ "$VER" == "" ];
then
   VER="1.0"
fi

if [ "$MODE" == "DEV" ];
then
    docker build --no-cache -f Dockerfilelocal -t mirbozorgi/cohort-service-dev:$VER .
    _IMG=mirbozorgi/cohort-service-dev:$VER _PROFILE=dev docker stack deploy -c docker-compose.yml mirbozorgi-cohort-service
else
    docker build --no-cache -t mirbozorgi/cohort-service:$VER .
    _IMG=mirbozorgi/cohort-service:$VER _PROFILE=prod docker stack deploy -c docker-compose.yml mirbozorgi-cohort-service
fi