---
title: RESTful API documentation with Spring REST Docs and RAML
theme: white   
revealOptions:
    transition: 'fade'
---

<!-- .slide: data-background="assets/title.png" -->

---

## it's epages

---

## Why Spring REST Docs?

- Uses a test-driven approach which guarantees accuracy
- Uses Asciidoctor by default
- Works with Spring MVC Test

---

<!-- .slide: data-background="assets/live-coding.jpeg" -->
## Spring REST Docs demo <!-- .element: style="color: white;" -->

---

<!-- .slide: data-background="assets/towards-public-api-doc.jpg" -->
## Towards a public API documentation <!-- .element: style="color: white;" -->

Note:
- different challenges
- bring tech writers in

---

## Bring tech writers in

- Tech writers should not edit descriptions in tests directly.
- Use externalized descriptors.
- Centralized yml files in `src/docs`.

---

##  Aggregate documentation

- One repository that composes all public documentation from the relevant microservices

Note:
- each service emits documentation
- how can we aggregate?

---

## Consistent test data

- In documenting tests, we use a test data catalog defined by TechWriting.
- Centralized yml files.

---

<!-- .slide: data-background="assets/achievements.jpg" -->
## First achievements <!-- .element: style="color:white;" -->

---

## First achievements

- [Static API documentation](http://docs.beyondshop.cloud/) available on our [developer portal](https://developer.epages.com)
- Good starting point to work with partners

---

<!-- .slide: data-background="assets/more.jpg" -->
## We want to go further <!-- .element: style="color:white;" -->

---

## We want to go further

- API docs should be a nice appetizer to start using our product <!-- .element: class="fragment" -->
- We cannot achieve this with a static documentation <!-- .element: class="fragment" -->
- <!-- .element: class="fragment" --> We need to go **interactive**!

---

## Why RAML?

- AsciiDoc as a markup language is hard to process <!-- .element: class="fragment" -->
- It is hard to get any further than static HTML <!-- .element: class="fragment" -->
- A technical exchange format is what we need <!-- .element: class="fragment" -->

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
## restdocs-raml demo <!-- .element: style="color: white;" -->

---

## Conclusion

- Adding restdocs-raml to an existing project is easy <!-- .element: class="fragment" -->
- A RAML representation of an API opens a lot of new possibilities <!-- .element: class="fragment" -->
- Leverage the tools available in the RAML ecosystem <!-- .element: class="fragment" -->

---

## Reference

<i class="fab fa-github"></i> Slides<br />
https://mduesterhoeft.github.io/spring-restdocs-raml-talk

<i class="fab fa-github"></i> Sample project<br />
https://github.com/mduesterhoeft/spring-restdocs-raml-talk

<i class="fab fa-github"></i> restdocs-raml<br />
https://github.com/ePages-de/restdocs-raml


---

## Thanks for your attention

Questions?

<i class="fab fa-twitter"></i><a href="https://twitter.com/zaddo"> @zaddo</a>

<i class="fab fa-twitter"></i><a href="https://twitter.com/epagesdevs"> @epagesdevs</a>

<i class="fab fa-gitter"></i><a href="https://twitter.com/epagesdevs"> https://gitter.im/restdocs-raml/restdocs-raml</a>
