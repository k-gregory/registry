#!/bin/sh
java -Dspring.profiles.active=production -Djava.security.egd=file:/dev/./urandom -jar /home/registry/server.jar
