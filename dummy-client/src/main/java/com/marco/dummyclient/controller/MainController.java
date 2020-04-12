package com.marco.dummyclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;



@RestController
@RequestMapping(path = "/api/employees")
public class MainController {
	
	@Autowired
	private WebClient webClient;
	
	@GetMapping
    public ResponseEntity<Mono<String>> getEmployeeAndDepartment() {
	
		Mono<String> mono = webClient.get()
        .uri("http://localhost:8095/api/callee/")
        .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("marcoclient"))
        .accept(MediaType.APPLICATION_STREAM_JSON)
        .retrieve()
        .bodyToMono(String.class);
		return new ResponseEntity<Mono<String>>(mono, HttpStatus.OK);
    }

}
