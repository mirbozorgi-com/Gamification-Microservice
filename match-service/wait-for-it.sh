#!/bin/bash
set -eux

declare -r CONFIG="mirbozorgiarsalan.com:8888/match-service/prod/master"
declare -r EUREKA="mirbozorgiarsalan.com:8761"
wait-for-url() {
    echo "Testing $1"
    timeout -t 12000 bash -c \
    'while [[ "$(curl -s -o /dev/null -L -w ''%{http_code}'' ${0})" != "200" ]];\
    do echo "Waiting for ${0}" && sleep 2;\
    done' ${1}
    echo "OK!"
    curl -I $1
}
wait-for-url http://${CONFIG}
wait-for-url http://${EUREKA}