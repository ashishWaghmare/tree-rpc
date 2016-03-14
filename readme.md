## Objective

Server is having tree stored which has to be served to different clients. Oneof the mechansim to serve this graph is by using Message Queue ( which is hornetq in this case). 

## Overview

### Graph

Every node is uniqully identified by UUID. Since this is distributed system. UUID will be assigned by Broker to make sure they dont conflict in global namespace. But overall structure looks like below.

Tree<T>{
 Node<T> root;
}

Node<T>{
 UUID id;
 T value;
 List<Node> childs;
}

### Messaging
Inspired by Actor Concurrency model in erlang/akka,  Incoming queue is message queue for Server and Outgoing queue is message queue for Client.
This is designed specially for one client but can be extended to N clients with N outoging queus from server.

Client1 --> graph service queue --> Server
Server --> temporary queue for each client 1 --> Client1

Client2 --> graph service queue --> Server
Server --> temporary queue for each client 2 --> Client2
.
.
.
Client N --> graph service queue --> Server
Server --> temporary queue for each client N --> Client N

### Request and Response

Any client can request for tree value using Request object which is transportded over Queue via JMS. Server will respond with value of Node and along with reference for child nodes. Client can use this reference to navigate further on tree. 

## Running 

This application is based on Spring Boot Hornetq starter application.

- Install a Java SDK
- Install gradle or use gradlew
- in.waghmare.ClientMain.java is primary entry point to start application.

### Building
On windows use gradlew.bat or else use ./gradlew 


### Steps to start one JVM 
* Open new shell/cmd prompt in windows.
* Go to directory of broker/server or client.
* Issue command
* gradle clean bootRun

### JVM order of starting application
* Start Broker first which has graph queue
* Start Server which can serve request for Tree
* Start Client which will periodically make request to server via broker.
* You can start more Clients as you want since every client creates seprate temporary queue for response.

## Further Improvements

- Write more test cases for Tree 
- Extend code for Graph

