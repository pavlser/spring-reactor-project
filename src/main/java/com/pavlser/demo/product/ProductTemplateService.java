package com.pavlser.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductTemplateService {

	@Autowired
	ReactiveMongoTemplate template;

	public Mono<Product> findById(String id) {
		return template.findById(id, Product.class);
	}

	public Flux<Product> findAll() {
		return template.findAll(Product.class);
	}

	public Mono<Product> save(Mono<Product> account) {
		return template.save(account);
	}

}
