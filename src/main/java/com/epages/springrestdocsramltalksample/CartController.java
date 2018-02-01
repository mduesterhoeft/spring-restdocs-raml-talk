package com.epages.springrestdocsramltalksample;

import static org.springframework.data.rest.webmvc.RestMediaTypes.TEXT_URI_LIST_VALUE;

import java.util.Optional;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epages.springrestdocsramltalksample.CartResourceResourceAssembler.CartResource;

import lombok.RequiredArgsConstructor;

@RepositoryRestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final EntityLinks entityLinks;

    private final CartResourceResourceAssembler cartResourceResourceAssembler;

    @PostMapping
    public ResponseEntity<Void> create() {
        Cart cart = cartRepository.save(new Cart());
        return ResponseEntity.created(entityLinks.linkForSingleResource(cart).toUri()).build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResource> get(@PathVariable Long cartId) {
        return cartRepository.findById(cartId)
                .map(cartResourceResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{cartId}/order")
    public ResponseEntity<?> order(@PathVariable Long cartId) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.setOrdered(true);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/{cartId}/products", consumes = TEXT_URI_LIST_VALUE)
    public ResponseEntity<?> addProducts(@PathVariable Long cartId, @RequestBody Resources<Object> resource) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    resource.getLinks().stream()
                            .map(link -> link.getHref().substring(link.getHref().lastIndexOf("/") + 1))
                            .map(Long::valueOf)
                            .map(productRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(product -> cart.getProducts().add(product));
                    return cartRepository.save(cart);
                })
                .map(c -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
