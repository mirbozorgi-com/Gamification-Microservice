#!/bin/bash
MODE=$1
VER=$2

if [ "$VER" == "" ];
then
   VER="1.0"
fi

if [ "$MODE" == "DEV" ];
then
    docker build --no-cache -f Dockerfilelocal -t mirbozorgi/leaderboard-service-dev:$VER .
    _IMG=mirbozorgi/leaderboard-service-dev:$VER _PROFILE=dev docker stack deploy -c docker-compose.yml mirbozorgi-leaderboard-service
else
    docker build --no-cache -t mirbozorgi/leaderboard-service:$VER .
    _IMG=mirbozorgi/leaderboard-service:$VER _PROFILE=prod docker stack deploy -c docker-compose.yml mirbozorgi-leaderboard-service
fi