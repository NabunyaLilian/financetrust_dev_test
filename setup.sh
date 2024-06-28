#!/bin/bash

./mvnw clean package

docker build -t stats-api .

docker run -p 8080:8080 stats-api
