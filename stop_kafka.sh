#!/bin/sh

DIR=$PWD/docker-compose-kafka

echo "Terminating all..."

docker-compose -f "$DIR"/kafka_broker_and_zookeeper.yml down

echo "Done!"