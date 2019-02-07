package com.pavlser.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import com.pavlser.demo.product.Product;
import com.pavlser.demo.product.ProductReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@EnableWebFlux
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	ProductReactiveRepository repository;
	
	@Autowired
	Environment environment;
	
	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		System.out.println("---> Populate some products");
		repository.saveAll(Flux.just(
			new Product("1", "Product 1", 123.99d),
			new Product("2", "Product 2", 99.17d),
			new Product("3", "Product 3", 444.11d))
		).subscribe();
		
		System.out.println("---> Query product");
		loadOne("1").subscribe(p -> {
			System.out.println(" > Product loaded: " + p);
		}, error -> {
			System.out.println(" > Error loading product: " + error);
		}, () -> {
			System.out.println(" > Query completed");
		});
	}
	
	Mono<Product> loadOne(String id) {
		WebClient client = WebClient.create("http://localhost:" 
			+ environment.getProperty("local.server.port"));
		Mono<Product> product = client.get()
			.uri("/api/product/{id}", id)
			.retrieve()
			.bodyToMono(Product.class);
		return product;
	}
	
}
