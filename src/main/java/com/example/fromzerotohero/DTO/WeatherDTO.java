package com.example.fromzerotohero.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDTO {
	
	@JsonProperty("list")
	List<WeatherDataDTO> data;

	public List<WeatherDataDTO> getData() {
		return data;
	}

	public void setData(List<WeatherDataDTO> data) {
		this.data = data;
	}
	

}
