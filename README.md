# Demo project for Spring Reactor

## Build and runn
```
mvn spring-boot:run
```

Open: http://localhost:8090/hello


# Using Reactor Project in Java

> Use Reactor 3 if you are allowed to use Java 8+, use RxJava 2 if you are stuck on Java 6+ or need your functions to throw checked exceptions

[David Karnok](https://twitter.com/akarnokd/status/780135681897197568)

## Reactor features:

![alexsderkach.io](http://alexsderkach.io/assets/posts/2017-04-22-comparing-java-8-rxjava-reactor/conclusion.png "alexsderkach.io")

[Alex Derkach](http://alexsderkach.io/comparing-java-8-rxjava-reactor/)

## Performance comparison:

![Reactor performance](https://pbs.twimg.com/media/CtOX-icWEAAJi1I.jpg "David Karnok Reactor Comparison")

[David Karnok](https://twitter.com/akarnokd/status/780135681897197568)

## But don't forget about old scool for-loop

![For-loop performance](https://pbs.twimg.com/media/Dw86dJEWwAAGjip.jpg "David Karnok Reactor Comparison")

[David Karnok](https://twitter.com/akarnokd/status/780135681897197568)

## Mastering Reactor

> To successfully work with WebFlux/Reactor make sure you understand:
> - What is backpressure
> - How to limit the number of data requested
> - How to limit to deal with buffer/overflow
> - What role parallelism plays
> - What are Schedulers
> - What are Hot and Cold publishers

[Bartosz Jedrzejwski](https://www.e4developer.com/2018/04/28/springs-webflux-reactor-parallelism-and-backpressure/)
