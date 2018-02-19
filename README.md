# How to produce RAML with Spring REST Docs using restdocs-raml

This repository is the basis for a talk introducing [restdocs-raml](https://github.com/ePages-de/restdocs-raml) - a project that adds RAML support for [Spring Rest Docs](https://github.com/spring-projects/spring-restdocs).

The talk shows why and how to use it.

## Presentation 

The slides are available in the [docs](docs/) folder on the `master` branch. They are also available through github pages https://mduesterhoeft.github.io/spring-restdocs-raml-talk/

## Branches

The `master` branch contains the initial state of the sample application.

The [restdocs-documented](https://github.com/mduesterhoeft/spring-restdocs-raml-talk/tree/restdocs-documented) branch contains the project documented with Spring REST Docs.

The [restdocs-raml-documented](https://github.com/mduesterhoeft/spring-restdocs-raml-talk/tree/restdocs-documented) branch contains the project documented with Spring REST Docs and `restdocs-raml`. 

## Running the application

```
git checkout restdocs-raml-documented
./gradlew bootRun
```