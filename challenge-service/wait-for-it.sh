#!/bin/bash
set -eux

declare -r HOST="127.0.0.1:8888/eureka-service/prod/master"

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