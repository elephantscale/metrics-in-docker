#!/bin/bash

#export CURRENT_USER="$(id -u):$(id -g)"

docker-compose  up -d

docker-compose  ps

echo -e "\n------------------------------------------------------------------------------------------------------"
echo -e "All services started!"
echo -e "Grafana UI : http://localhost:3000"
echo -e "    username: admin ,   password: kafka"
echo -e "\n------------------------------------------------------------------------------------------------------"

exit 0