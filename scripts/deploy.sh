#!/usr/bin/env bash

mvn clean install -U -P prod

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/order-service-1.0.jar \
    kvn@172.26.0.62:/home/kvn/

echo 'Restart server...'




echo 'Bye'
