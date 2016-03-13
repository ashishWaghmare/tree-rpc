
    Challenge #1: RPC Service
Create an async RPC service and client that communicate over a message bus.
The goal of the RPC service is to parse trees using recursive decent.

Challenge #2: Family Tree

Semi-structure-to-kickstart-ideas:

Relationship {
SPOUSE
MOTHER
FATHER
SIBLING
GRANDPARENT
OTHER
NONE
}

public ? getFamilyTree(); // A call to <Person>.getFamilyTree() will return a graphic (either in text or image) of the person's familial connections with names and relationship shown.


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

in.waghmare.ClientMain.java is entry point to application which starts embedded HornetQ broker and create two queues incoming and outgoing.
Inspired by Actor Concurrency model in erlang/akka,  Incoming queue is message queue for Server and Outgoing queue is message queue for Client.
This is designed specially for one client but can be extended to N clients with N outoging queus from server.

Server --> outgoing queue --> Client

Client --> incoming queue --> Server

### Request and Response

Any client can request for tree value using Request object which is transportded over Queue via JMS. Server will respond with value of Node and along with reference for child nodes. Client can use this reference to navigate further on tree. 

## Running in.waghmare.ClientMain

This application is based on Spring Boot Hornetq starter application.

- Install a Java SDK
- Install gradle or use gradlew
- in.waghmare.ClientMain.java is primary entry point to start application.

## Building
- On windows use 
- gradlew.bat bootRun
- ./gradlew bootRun

Run:

## Further Improvements

- Make all broker client and server into 3 different JVM
- Enable N client by passing response QueueName in request object and server using it to reply back.
- Write testcases for TreeService


