---
title: RESTful API documentation with Spring REST Docs and RAML
theme: white
revealOptions:
    transition: 'fade'
---

<!-- .slide: data-background="assets/restdocs-raml.jpg" -->
## Documenting RESTful APIs with Spring REST Docs and RAML

---

<!-- .slide: data-background="assets/epages-devs.png" -->


---

## Why Spring REST Docs?

- uses a test-driven approach which guarantees accuracy
- uses Asciidoctor by default
- works with Spring MVC Test 

---

<!-- .slide: data-background="assets/live-coding.jpeg" -->
## Spring REST Docs Demo

---

## Spring REST Docs in the real world

- how do we compile an API documentation in a microservice context
- how do we include tech writing into the workflow
- good test data usage

---

## Current status

Static API documentation available on our 

[developer portal](https://developer.epages.com) 

[http://docs.beyondshop.cloud](http://docs.beyondshop.cloud)

Good starting point to work with partners.

---

## Where do we want to go

- `API first` means our API is part of our product
- a good API is important for us
- the documentation should be a nice appetizer to start using our product
- we cannot achieve this with a static documentation <!-- .element: class="fragment" -->
- we need to go <!-- .element: class="fragment" --> **interactive** <!-- .element: class="fragment" -->

---

## Why RAML?

- AsciiDoc as a markup language is hard to parse 
- it is hard to get any further than static HTML 
- a technical exchange format is what we need <!-- .element: class="fragment" -->
- we already had experience with RAML <!-- .element: class="fragment" -->

---

<!-- .slide: data-background="assets/raml.png" -->

---

## RAML by example

```yaml
#%RAML 1.0
title: Hello world # required title

/greeting: # optional resource
  get: # HTTP method declaration
    responses: # declare a response
      200: # HTTP status code
        body: # declare content of response
          application/json: # media type
            # structural definition of a response (schema or type)
            type: object
            properties:
              message: string
            example: # example how a response looks like
              message: "Hello world" 
```

---

## Introducing restdocs-raml

We build `restdocs-raml` 
- to keep the benefits of Spring REST Docs
- to get a RAML file for our API to process further

---

<!-- .slide: data-background="assets/restdocs-raml.jpeg" -->

---