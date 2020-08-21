#!/bin/sh

DIR=$PWD/docker-compose-kafka

echo "Spinning kafka broker and zookeeper locally..."

docker-compose -f "$DIR"/kafka_broker_and_zookeeper.yml up -d

echo "To see the docker containers running, simply run:
                      $ docker ps -a"