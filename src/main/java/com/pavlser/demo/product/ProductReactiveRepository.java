package com.pavlser.demo.product;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductReactiveRepository extends ReactiveMongoRepository<Product, String> {

	Flux<Product> findAllByName(String name);

	Mono<Product> findFirstById(String id);
	
}