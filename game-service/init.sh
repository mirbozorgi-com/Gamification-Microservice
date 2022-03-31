#!/bin/bash
MODE=$1
VER=$2

if [ "$VER" == "" ];
then
   VER="1.0"
fi

if [ "$MODE" == "DEV" ];
then
    docker build --no-cache -f Dockerfilelocal -t mirbozorgi/game-service-dev:$VER .
    _IMG=mirbozorgi/game-service-dev:$VER _PROFILE=dev docker stack deploy -c docker-compose.yml mirbozorgi-game-service
else
    docker build --no-cache -t mirbozorgi/game-service:$VER .
    _IMG=mirbozorgi/game-service:$VER _PROFILE=prod docker stack deploy -c docker-compose.yml mirbozorgi-game-service
fi