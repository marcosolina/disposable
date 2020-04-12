package com.marco.dummyclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	WebClient webClient(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
		
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
		return WebClient.builder()
				.apply(oauth2.oauth2Configuration())
				.build();
	}

	@Bean
	public ClientRegistrationRepository clientRegistrations() {
		/*
		 * Registering the "client" credential to use when I want to retrieve the token
		 */
		ClientRegistration clientRegistration = ClientRegistration
				.withRegistrationId("marcoclient")
				.clientId("client-client").clientSecret("bace9a2f-d659-45b0-8170-5b40c7f5e600")
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.clientAuthenticationMethod(ClientAuthenticationMethod.POST)
				.tokenUri("http://localhost:8091/auth/realms/test-realm/protocol/openid-connect/token")
				.build();

		//TODO retrieve from a database//TODO retrieve from a database
		return new InMemoryClientRegistrationRepository(clientRegistration);
	}
	
}
