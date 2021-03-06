<!-- logo -->
<p align="center">
  <img width='300' src="https://mirbozorgi.com/wp-content/uploads/2020/10/Finally_Done-removebg-preview-300x293.png">
</p>

<!-- tag line -->
<h3 align='center'> A Microservice with Spring! </h3>

<!-- primary badges -------------------------------------->
<p align="center">
  <!-- version -->

## How does it work?

- First install MongoDB, PostgresSQL and Redis on your OS
- Create Databases needed in the
  config ([GitHub]( https://github.com/mirbozorgi-com/Gamification-Microservice-Config-Repo))
- Start Config Service
- Start Eureka Service as register service
- Start whatever service you want based of your preferences

## Features and Stacks V 1.0.0

⚡ Spring Cloud `Greenwich.RELEASE version `

⚡ Spring `2.1.2.RELEASE version `

⚡ Java `1.8`

⚡ Zuul and Spring Cloud Api Gateway as `Api Gateway `

⚡ OpenFeign

⚡ Actuator

⚡ Spring Cloud Config
with [GitHub]( https://github.com/mirbozorgi-com/Gamification-Microservice-Config-Repo)

⚡ Eureka as `Registery Service `

⚡ All Services has their databases, and they are independent of other database's schema


<hr style="border:2px solid gray"> </hr>

☢️ FeignError Service : With this service all the errors in feign and chaining will be centralized,
and you can detect the bottleneck manually(after implementation of Sentry.io, this service will be
terminated.)

☢️ Challenge Service : You can create multiple challenges in order to user participation.

☢️ Cohort Service : You can assign cohort to users and do a/b testing

☢️ Game Service : This is the basic service, and you must register every game or online site first.

☢️ Gift Code Service:  You can define gift code for evert game entity

☢️ Item Shop Service: You can define multiple item shop per game entity

☢️ Leaderboard Service: You can create multiple leaderboards based on the wallet entities.

☢️ Match Service: You can persist the match data for users and also define credential for joining

☢️ Notification Service: You can do push notification in the app and also in the notification center

☢️ User Service: User Profiling and so on, so forth.

## ☕ V 1.5.0 Future Features:

- Adding Lombok and usage of Lambda
- Usage of multiple design pattern to enhance the code quality
- Add MapStruct for all Mappers
- Update Spring Cloud to `2021.0.1`
- Update Java to `17`
- Update Spring Boot to `2.6.6`

## ☕ V 1.8.0 Future Features:

- Adding Unit tests with Junit 5
- Adding Integration tests with Junit 5
- Adding PostMan Packages for acceptance test.

## ☕ Future Features:

- Grafana and Prometheus to `Monitor the services`
- Integrating Kafka and replace feign services with Events which published to kafka topics
- Dockerized the whole system (50% has been done)
- Integrating ELK (Elastic search, Logstash, Kibana) in order to centralize the logs
- Integrating Sentry.io in order to centralize the exceptions
- Zipkin as `Distributed Tracing Service `
- Hystrix

<br/>

## 💙 Contributing

PR's are welcome !

Found a Bug ? Create an Issue.

Chat on [Telegram](https://t.me/arsalanmrz)

<br/>

## 💖 Like this project ?

Leave a ⭐ If you think this project is cool.

[Share with the world](https://www.linkedin.com/shareArticle?mini=true&url=https://github.com/mirbozorgi-com/Gamification-Microservice)
✨

<br/>

## 👨‍💻 Author

### Arsalan Mirbozorgi

[LinkedIn](https://linkedin.com/in/arsalan-mirbozorgi/ "Arsalan Mirbozorgi")

<br/>

## 🍁 Licence

**[Click](https://github.com/mirbozorgi-com/Gamification-Microservice/blob/1.0.0/LICENSE)**
