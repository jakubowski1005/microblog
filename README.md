# Bloget [![Build Status](http://3.128.27.62:8080/buildStatus/icon?job=microblog%2Fmaster&build=last)](http://3.128.27.62:8080/job/microblog/job/master/last/)
Twitter-like microblog based on microservices architeture.

## Table of content

- [Setup](#setup)
- [Technologies, tools and libraries](#technologies-tools-and-libraries)
- [Author](#author)
- [Todo](#todo)

## Setup

Clone the repository:

```
git clone https://github.com/jakubowski1005/microblog.git
```
Then open the directory:

```
cd microblog
```

And run:

//TODO Add script


## Technologies, tools and libraries

- Java 11
- Spring Boot 2.2.6
- Spring Cloud (Eureka, Zuul, oAuth2)
- Gradle 7.0
- MongoDB
- Lombok
- Docker

## Author

- Artur Jakubowski

## Todo

- [ ] make auth and post services reactive with weblux and mongodb
- [ ] implement email verification, notification etc.
- [ ] implement v1 user interface with Angular or React
- [ ] create e2e tests and include them in Jenkins pipeline
- [ ] deploy app on rasberry pi cluster with docker swarm
