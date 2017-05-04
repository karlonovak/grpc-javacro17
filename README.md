**####gRPC, Thrift, Spring Boot MVC REST, JAX-WS (CXF) examples and comparison**

This repository was made for [JavaCro'17](http://2017.javacro.hr/) conference session "Putting REST to rest with gRPC" by Karlo Novak (that's me!). 

It contains 4 server projects:

* grpc-javacro

* thrift-javacro

* spring-javacro

* jaxws-javacro


As well as load/stress tests for each of them:

* gatling-test-javacro-grpc

* gatling-test-javacro-thrift

* gatling-test-javacro-springboot

* gatling-test-javacro-jaxws


---


**####gRPC, Thrift, Spring Boot, JAX-WS (CXF) server examples (Java)**

Examples show following technologies:

1. [gRPC](http://www.grpc.io/)

2. [Thrift](https://thrift.apache.org)

3. [Spring Boot](https://projects.spring.io/spring-boot)

4. [JAX-WS, CXF](http://cxf.apache.org/)

All server implementations accept simple POJO *GreetingRequest* as a request, and return *GreetingResponse* as a response to make the comparison as fair as it can be.  Also, client connections are kept alive in all the implementations to make sure each technology in test has the same starting position.


---


**####Load/stress tests (Scala/Gatling)**

![alt-text](http://gatling.io/wp-content/uploads/2017/02/Gatling-logo.png "Gatling logo")

All the tests are implemented with Scala and [Gatling](http://gatling.io/).

Custom protocols for gRPC and Thrift are based upon grpc-gatling [project](https://github.com/tamediadigital/grpc-gatling/)

####Test case and environment
* **Hardware**: Intel(R) Core(TM) i5-4200M at 2.50GHz (4 threads using HyperThreading), 8GB RAM
* **Technology**: gRPC is the main technology under test and it's being compared to Thrift as a most popular RPC/Binary serialization framework. Also, gRPC is being tested against Spring Boot (MVC) as one of the most popular technologies for building Java based HTTP APIs.
* **Test scenario**: Each test uses 50 concurrent users which iterate 20.000 times sending a simple POJO *GreetingRequest* as a request, and return *GreetingResponse** as a response. In total, servers process 1.000.000 (1 million) requests.
* **Points of measurement**: Throughput in **req/s**, request-response speed under heavy load in **ms**


---


####Test results

**GPRC:**
![alt-text](assets/grpc.png "gRPC")

**Thrift:**
![alt-text](assets/thrift.png "Thrift")

**Spring Boot (MVC REST):**
![alt-text](assets/spring.png "Spring")

**JAX-WS:**
![alt-text](assets/jaxws.png "JAX-WS")
