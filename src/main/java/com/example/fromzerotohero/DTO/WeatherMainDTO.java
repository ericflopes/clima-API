package com.example.fromzerotohero.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherMainDTO {
	@JsonProperty("temp_max")
	private String tempmax;
	@JsonProperty("temp_min")
	private String tempMin;
	private String pressure;
	private String humidity;

	public String getTempmax() {
		return tempmax;
	}

	public void setTempmax(String tempmax) {
		this.tempmax = tempmax;
	}

	public String getTempMin() {
		return tempMin;
	}

	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

}
