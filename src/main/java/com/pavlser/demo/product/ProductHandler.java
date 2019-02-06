package com.pavlser.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class ProductHandler {
	
	@Autowired
	ProductReactiveRepository repository;

	public Mono<ServerResponse> getById(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<Product> mono = repository.findFirstById(id);
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(mono, Product.class));
	}

}
