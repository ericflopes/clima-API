package com.example.fromzerotohero.api;

import javax.xml.ws.Response;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.fromzerotohero.DTO.WeatherDTO;

@Component
public class GitHubClient {

	private static final String URL_CLIMA = "http://api.openweathermap.org/data/2.5/forecast?q={cidade},br&units=metric&mode=json&appid={key}&lang=pt";
	private static final String KEY = "67bd485ee280b5f40c642360a9e1c638";
	private final RestTemplate restTemplate;
	
	public GitHubClient(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
		}
		
	public WeatherDTO retornaClima(String cidade){
		return this.restTemplate.getForObject(URL_CLIMA, WeatherDTO.class,cidade,KEY);
	}
	
	

}
