package com.pavlser.demo;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pavlser.demo.product.ProductHandler;

@Configuration
public class Router {

	@Bean
	public RouterFunction<ServerResponse> route(ProductHandler handler) {
		return RouterFunctions
			.route(GET("/"), req -> ServerResponse.ok()
				.contentType(MediaType.TEXT_HTML)
				.body(BodyInserters.fromObject("Hello, Spring Reactor!")))
			.andRoute(GET("/api/product/{id}")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), handler::getById);
	}
	
}
