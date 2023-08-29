#!/bin/bash
docker image build -t testrestservice:latest -f src/main/docker/build.Dockerfile .  && docker-compose -f src/main/docker/app.yml -p svc up -d
