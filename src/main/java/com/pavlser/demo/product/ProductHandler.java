package com.pavlser.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {
	
	@Autowired
	ProductReactiveRepository repository;
	
	public Mono<ServerResponse> allProducts(ServerRequest request) {
		Flux<Product> products = repository.findAll();
		return ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(products, Product.class);
	}

	public Mono<ServerResponse> getById(ServerRequest request) {
		String id = request.pathVariable("id");
		return repository.findFirstById(id)
			.flatMap(product -> 
				ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(product), Product.class))
			.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> createProduct(ServerRequest request) {
		return ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromPublisher(Mono.just("OK"), String.class));
	}

}
