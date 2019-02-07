package com.pavlser.demo.config;

//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
public class WebFluxSecurityConfig {

	/*@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.csrf().disable().authorizeExchange().pathMatchers(HttpMethod.POST, "/employees/update").hasRole("ADMIN")
				.pathMatchers("/**").permitAll().and().httpBasic();
		return http.build();
	}*/
	
	/*
	return http.authorizeExchange()
			  .pathMatchers("/admin").hasAuthority("ROLE_ADMIN")
			  .anyExchange().authenticated()
			  .and().formLogin()
			  .and().build();
	*/
	
/*	
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
*/
}
