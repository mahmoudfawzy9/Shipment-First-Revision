#!/bin/bash

rm star-shipment-service.log
sudo nohup java -jar ./build/libs/star-be-shipment-service.jar -server > ./star-shipment-service.log &