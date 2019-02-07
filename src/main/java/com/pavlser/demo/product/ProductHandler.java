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
	
	public Mono<ServerResponse> createProduct(ServerRequest request) {
		return ServerResponse.ok()
				//.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(Mono.just("OK"), String.class));
	}

}

/*
Flux.merge(
stream1.onErrorResume(e -> Flux.empty())
  .map(v -> Tuples.of(1, v)),
stream2.onErrorResume(e -> Flux.empty())
  .map(v -> Tuples.of(2, v)),
stream3.onErrorResume(e -> Flux.empty())
  .map(v -> Tuples.of(3, v))
)
.buffer(3)
.subscribe(combineResult -> ...);
*/

/*
Flux<Greeting> greetingFlux = Flux.fromStream(Stream.generate(() -> new Greeting("Hello @" + Instant.now().toString())));
Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
return Flux.zip(greetingFlux, durationFlux).map(Tuple2::getT1);
*/
