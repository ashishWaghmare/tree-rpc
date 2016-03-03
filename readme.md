## Objective

Server is having tree stored which has to be served to different clients. Oneof the mechansim to serve this graph is by using Message Queue ( which is hornetq in this case). 


## Overview

Any client can request for tree value using Request object which is transported over Queue via JMS. Server will respond with value of Node and along with reference for child nodes. Client can use this reference to navigate further on tree. 



## Prereqs

- Install a Java SDK
- Install gradle or use gradlew
- BrokerStart is primary entry point to start application.

## Building
- On windows use 
- gradlew.bat bootRun
- ./gradlew bootRun

Run:


