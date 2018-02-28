#!/bin/bash
docker container stop javapoc
docker run --name javapoc -d -p 8082:8082 siddhirajpantoji/javapoc  
