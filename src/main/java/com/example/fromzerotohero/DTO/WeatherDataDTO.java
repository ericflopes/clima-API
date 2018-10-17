package com.example.fromzerotohero.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDataDTO {

	private WeatherMainDTO main;
	
	@JsonProperty("weather")
	private List<WeatherDescriptionDTO> desc;
	
	private WeatherWindDTO wind;
	
	@JsonProperty("dt_txt")
	private String data;

	public WeatherMainDTO getMain() {
		return main;
	}

	public void setMain(WeatherMainDTO main) {
		this.main = main;
	}

	public List<WeatherDescriptionDTO> getDesc() {
		return desc;
	}

	public void setDesc(List<WeatherDescriptionDTO> desc) {
		this.desc = desc;
	}

	public WeatherWindDTO getWind() {
		return wind;
	}

	public void setWind(WeatherWindDTO wind) {
		this.wind = wind;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
