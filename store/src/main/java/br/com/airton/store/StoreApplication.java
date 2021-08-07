package br.com.airton.store;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableResourceServer
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	public RequestInterceptor getAuthenticationInterceptor(){
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if(authentication==null){
					return;
				}

				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
				String token = details.getTokenValue();
				requestTemplate.header("Authorization", String.format("Bearer %s", token));

			}
		};
	}

	/* air01 - IMPLEMENTAÇÃO USANDO REST TEMPLATE COM RIBBON E LOADBALANCER

 	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	//RestTemplate restTemplate = restTemplateBuilder.build();

	/**
	 * @LoadBalanced: Essa anotação vai dar a inteligência para essa instância de RestTemplate conseguir resolver URL's do Eureka e realizar o balanceamento (implementado pelo Ribbon).
	 * Ele faz o CLIENT-SIDE LOAD-BALANCING, que é a funcionalidade de ficar o tempo inteiro, cada requisição que fizermos para o fornecedor, redirecionando para um IP e porta diferente.
	 * @return
	 */
	/*
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		//return new RestTemplate();
		return restTemplateBuilder.build();
	}
	*/


}
