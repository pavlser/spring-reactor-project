package com.pavlser.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.pavlser.demo.product.Product;
import com.pavlser.demo.product.ProductReactiveRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	ProductReactiveRepository repository;
	
	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		repository.saveAll(Flux.just(
			new Product("1", "Product 1", 123.99d),
			new Product("2", "Product 2", 99.17d),
			new Product("3", "Product 3", 444.11d))
		).subscribe();
	}
}
