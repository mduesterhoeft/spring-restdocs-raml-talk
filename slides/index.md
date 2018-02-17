---
title: RESTful API documentation with Spring REST Docs and RAML
theme: black
revealOptions:
    transition: 'fade'
---

<!-- .slide: data-background="assets/restdocs-raml.jpg" -->
## Documenting RESTful APIs with Spring REST Docs and RAML

---

## About us - and the project

- Online shop software
- New ecommerce infrastructure based on microservices
- API-first approach
- Development in distributed teams

---

## Why Spring REST Docs?

- Uses a test-driven approach which guarantees accuracy
- Uses Asciidoctor by default
- Works with Spring MVC Test

---

<!-- .slide: data-background="assets/live-coding.jpeg" -->
## Spring REST Docs demo

---

## Can we pleaaaase...

- Compile a complete documentation out of all these microservices projects?
- Ensure that we just cover the public stuff?
- Inform TechWriting about API changes?
- Make sure that TechWriting can change property descriptions without touching the code?
- Reuse content?
- Generate realistic and consistent example responses?

---

## Sure, we can!

- One repository that composes all public documentation from the relevant microservices
- Doc changes notifier via mail
- Placeholders in centralised .yml files
- Templates for https requests
- .yml file in a centralised repository representing the test data that can be reused by all microservices

---

## First milestones done

Static API documentation available on our

[developer portal](https://developer.epages.com)

[http://docs.beyondshop.cloud](http://docs.beyondshop.cloud)

Good starting point to work with partners.

---

## We want more

- `API first` means our API is part of our product
- A good API is important for us
- The documentation should be a nice appetizer to start using our product
- We cannot achieve this with a static documentation <!-- .element: class="fragment" -->
- We need to go <!-- .element: class="fragment" --> **interactive** <!-- .element: class="fragment" -->

---

## Why RAML?

- AsciiDoc as a markup language is hard to parse
- It is hard to get any further than static HTML
- A technical exchange format is what we need <!-- .element: class="fragment" -->
- We already have experience with RAML <!-- .element: class="fragment" -->

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

We built [`restdocs-raml`](https://github.com/ePages-de/restdocs-raml)
- to keep the benefits of Spring REST Docs
- to get a RAML file for our API to process further

---

<!-- .slide: data-background="assets/live-coding.jpeg" -->
## restdocs-raml demo

---

## Thanks for your attention

Questions?
