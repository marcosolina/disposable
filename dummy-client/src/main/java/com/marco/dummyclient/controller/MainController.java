package com.marco.dummyclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;



@RestController
@RequestMapping(path = "/api/client")
public class MainController {
	
	@Autowired
	private WebClient webClient;
	
	@GetMapping("/{id}")
    public ResponseEntity<Mono<String>> getEmployeeAndDepartment(@PathVariable("id") String id) {
	
		/*
		 * Calling another microservice using the Oauth Credential defined for the "marcosuper" cliet 
		 */
		Mono<String> mono = webClient.get()
        .uri("http://localhost:8095/api/callee/")
        .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("marcoclient"))
        .accept(MediaType.APPLICATION_STREAM_JSON)
        .retrieve()
        .bodyToMono(String.class).map(s -> "Hello from the client\n" + s);
		return new ResponseEntity<Mono<String>>(mono, HttpStatus.OK);
    }

}
