#!/bin/bash

SRC_DIR=$(cd "$(dirname "$0")/.."; pwd)
echo "Running on ${SRC_DIR}"

[[ -d database ]] || mkdir -v database

docker ps -a --format "{{.Names}}" | grep "^webevents-db$" > /dev/null

DB_DOCKER_STATUS=$?

[[ $DB_DOCKER_STATUS -eq 0 ]] || \
  docker run  -p "5433:5432" \
              -v "$(pwd)/database:/var/lib/postgresql/data" \
              --name webevents-db \
              -e POSTGRES_USER=webevents \
              -e POSTGRES_PASSWORD=webevents \
              -d \
              postgres
[[ $DB_DOCKER_STATUS -eq 0 ]] && docker start webevents-db

[[ -d mq ]] || mkdir -v mq

docker ps -a --format "{{.Names}}" | grep "^webevents-mq$" > /dev/null

DB_DOCKER_STATUS=$?

[[ $DB_DOCKER_STATUS -eq 0 ]] || \
  docker run  -v "$(pwd)/mq:/var/lib/rabbitmq" \
              --hostname webevents-mq \
              -p 5672:5672 \
              --name webevents-mq \
              -d \
              rabbitmq:3
[[ $DB_DOCKER_STATUS -eq 0 ]] && docker start webevents-mq

docker run --rm -p "8080:8080" \
                -u $(id -u):$(id -g) \
                -v $(pwd):/usr/bin/app:rw \
                --name webevents-web \
                --link webevents-db \
                --link webevents-mq \
                euranova/docker-gradle \
                gradle $@
