#!/usr/bin/env bash

mvn package
docker build --tag=testhotswapping .
docker run --publish=8080:8080 --rm=true --name=testhotswapping testhotswapping