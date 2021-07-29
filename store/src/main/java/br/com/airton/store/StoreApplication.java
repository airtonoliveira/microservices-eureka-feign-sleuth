package br.com.airton.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
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
