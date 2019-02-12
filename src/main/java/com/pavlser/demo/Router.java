package com.pavlser.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pavlser.demo.product.ProductHandler;

import reactor.core.publisher.Mono;

@Configuration
public class Router {

	List<String> registeredPath;
	
	@Bean
	public RouterFunction<ServerResponse> route(ProductHandler handler) {
		SimpleRouteBuilder builder = new SimpleRouteBuilder()
			.get("/", this::welcome)
			.get("/api/v1/products", handler::allProducts)
			.get("/api/v1/product/{id}", handler::getById)
			.post("/api/v1/product", handler::createProduct);
		registeredPath = builder.getPath();
		return builder.build();
	}
	
	public Mono<ServerResponse> welcome(ServerRequest request) {
		String s = "Hello from Spring Reactor!\n\n"
			+ "Please check urls:\n"
			+ String.join("\nhttp://localhost:8090", registeredPath);
		return ServerResponse.ok()
			.contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromPublisher(Mono.just(s), String.class));
	}
	
}

class SimpleRouteBuilder {
	
	RouterFunctions.Builder builder;
	List<String> paths;
	
	public SimpleRouteBuilder() {
		builder = RouterFunctions.route();
		paths = new ArrayList<>();
	}
	
	public SimpleRouteBuilder get(String path, HandlerFunction<ServerResponse> handler) {
		builder.GET(path, handler);
		paths.add(path);
		return this;
	}
	
	public SimpleRouteBuilder post(String path, HandlerFunction<ServerResponse> handler) {
		builder.POST(path, handler);
		paths.add(path);
		return this;
	}
	
	public RouterFunction<ServerResponse> build() {
		return builder.build();
	}
	
	public List<String> getPath() {
		return paths;
	}
	
}