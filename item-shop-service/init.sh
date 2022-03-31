#!/bin/bash
MODE=$1
VER=$2

if [ "$VER" == "" ];
then
   VER="1.0"
fi

if [ "$MODE" == "DEV" ];
then
    docker build --no-cache -f Dockerfilelocal -t mirbozorgi/item-shop-service-dev:$VER .
    _IMG=mirbozorgi/item-shop-service-dev:$VER _PROFILE=dev docker stack deploy -c docker-compose.yml mirbozorgi-item-shop-service
else
    docker build --no-cache -t mirbozorgi/item-shop-service:$VER .
    _IMG=mirbozorgi/item-shop-service:$VER _PROFILE=prod docker stack deploy -c docker-compose.yml mirbozorgi-item-shop-service
fi