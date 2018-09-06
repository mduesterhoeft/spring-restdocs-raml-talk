[![GitPitch](https://gitpitch.com/assets/badge.svg)](https://gitpitch.com/mduesterhoeft/spring-restdocs-raml-talk)

# Documenting RESTful APIs with Spring REST Docs and RAML

This repository is the basis for a talk introducing [restdocs-raml](https://github.com/ePages-de/restdocs-raml) - a project that adds RAML support for [Spring Rest Docs](https://github.com/spring-projects/spring-restdocs).

See also https://2018.springio.net/sessions/documenting-restful-apis-with-spring-rest-docs-and-raml

The talk shows why and how to use it.

There is also a recording from the Spring IO 2018 in Barcelona - [![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/VwKc34W96Cw/0.jpg)](https://www.youtube.com/watch?v=VwKc34W96Cw)

## Presentation 

https://gitpitch.com/mduesterhoeft/spring-restdocs-raml-talk

## Branches

The `master` branch contains the initial state of the sample application.

The [restdocs-documented](https://github.com/mduesterhoeft/spring-restdocs-raml-talk/tree/restdocs-documented) branch contains the project documented with Spring REST Docs.

The [restdocs-raml-documented](https://github.com/mduesterhoeft/spring-restdocs-raml-talk/tree/restdocs-documented) branch contains the project documented with Spring REST Docs and `restdocs-raml`. 

## Running the application

```
git checkout restdocs-raml-documented
./gradlew bootRun
```
