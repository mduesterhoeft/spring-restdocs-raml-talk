---
title: RESTful API documentation with Spring REST Docs and RAML
theme: black
revealOptions:
    transition: 'fade'
showSlideNumber: true    
---

<!-- .slide: data-background="assets/restdocs-raml.jpg" -->
## Documenting RESTful APIs with Spring REST Docs and RAML <!-- .element: style="text-shadow: 3px 3px black;" -->

---

<!-- .slide: data-background="assets/about-us.jpg" -->
<!-- .element: style="text-shadow: 3px 3px black;" -->

---

## Why Spring REST Docs?

- Uses a test-driven approach which guarantees accuracy
- Uses Asciidoctor by default
- Works with Spring MVC Test

---

<!-- .slide: data-background="assets/live-coding.jpeg" -->
## Spring REST Docs demo <!-- .element: style="text-shadow: 3px 3px black;" -->

---

<!-- .slide: data-background="assets/dream.jpg" -->
## TechWriters have dreams <!-- .element: style="text-shadow: 3px 3px black;" -->

---

## Can we compile a complete documentation?

---

##  Aggregate service documentation

One repository that composes all public documentation from the relevant microservices

---

## I'd love to be informed about API changes...

---

## Notification via email

TechWriting owns content in `src/docs` in every service and is notified about .changes

---

## I'd like to change descriptions without touching the code...

---

## Externalize texts from tests

Put texts into centralized yml files in `src/docs`.

---

## Can we generate realistic and consistent example responses?

---

## Consistent test data

- In documenting tests, we use a test data catalog defined by TechWriting.
- Centralized yml files.

---

<!-- .slide: data-background="assets/achievements.jpg" -->
## First achievements <!-- .element: style="text-shadow: 3px 3px black;" -->

- [Static API documentation](http://docs.beyondshop.cloud/) available on our
[developer portal](https://developer.epages.com)
- Good starting point to work with partners

---

<!-- .slide: data-background="assets/more.jpg" -->
## We want more <!-- .element: style="text-shadow: 3px 3px black;" -->

- API docs should be a nice appetizer to start using our product <!-- .element: class="fragment" -->
- We cannot achieve this with a static documentation <!-- .element: class="fragment" -->
- <!-- .element: class="fragment" --> We need to go **interactive**!

---

## Why RAML?

- AsciiDoc as a markup language is hard to process <!-- .element: class="fragment" -->
- It is hard to get any further than static HTML <!-- .element: class="fragment" -->
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
- To keep the benefits of Spring REST Docs
- To get a RAML file for our API to process further

---

<!-- .slide: data-background="assets/restdocs-raml-repo.png" style="align:top" -->

---

<!-- .slide: data-background="assets/live-coding.jpeg" -->
## Apply restdocs-raml <!-- .element: style="text-shadow: 3px 3px black;" -->

---

## Conclusion

- Adding  restdocs-raml to an existing project is easy <!-- .element: class="fragment" -->
- A RAML representation of an API opens lots of possibilities <!-- .element: class="fragment" -->
- Leverage the tools available in the RAML ecosystem <!-- .element: class="fragment" -->

---

## Reference

- Slides https://mduesterhoeft.github.io/spring-restdocs-raml-talk/
- `restdocs-raml` https://github.com/ePages-de/restdocs-raml
- Sample project https://github.com/mduesterhoeft/spring-restdocs-raml-talk


---

## Thanks for your attention

Questions?
