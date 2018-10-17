package com.example.fromzerotohero.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fromzerotohero.DTO.ResultadoClimaDTO;
import com.example.fromzerotohero.DTO.WeatherDTO;
import com.example.fromzerotohero.DTO.WeatherDataDTO;
import com.example.fromzerotohero.api.GitHubClient;
import com.example.fromzerotohero.entidades.ClimaEntidade;
import com.example.fromzerotohero.persistencia.ClimaPersistencia;

@Service
public class ClimaService {

	@Autowired
	GitHubClient client;
	
	@Autowired
	ClimaPersistencia repo;
	
	Map<String, String> datas;
	
	private String date;
	 public ClimaService(){
		this.date = converteData(new Date());
	}
	
	@Transactional
	public List<ResultadoClimaDTO> retornaClima(String cidade){
		List<ResultadoClimaDTO> climas = new ArrayList<>();
		if(isDataECidadeExistente(cidade,date)) {
			List<ClimaEntidade> tempos = repo.buscarTodos(cidade);
			for(ClimaEntidade c : tempos) {
				ResultadoClimaDTO r = converteEntidadeParaDto(c);
				climas.add(r);
			}
		}else {
			datas = new HashMap<>();
			WeatherDTO tempo = client.retornaClima(cidade);
			List<WeatherDataDTO> dates = tempo.getData();
			for (WeatherDataDTO d : dates) {
				ResultadoClimaDTO resultado = new ResultadoClimaDTO();
				if (!isNovaData(d.getData())) {
					resultado.setData(d.getData().substring(0, 10));
					resultado.setHumidity(d.getMain().getHumidity());
					resultado.setPressure(d.getMain().getPressure());
					resultado.setTempMin(d.getMain().getTempMin());
					resultado.setTempMax(d.getMain().getTempmax());
					resultado.setWind(d.getWind().getSpeed());
					resultado.setMain(d.getDesc().get(0).getMain());
					resultado.setIcon(d.getDesc().get(0).getIcon());
					resultado.setCidade(cidade);
					climas.add(resultado);
					repo.salvarClimas(converteDtoParaEntidade(resultado,cidade));
				}
			}
		}
		return climas;
	}

	public boolean isNovaData(String data) {
		String apenasData = data.substring(0, 10);
		if (datas.containsKey(apenasData)) {
			return true;
		} else {
			datas.put(apenasData, apenasData);
			return false;
		}
	}
	
	public Date converteData(String data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = format.parse(data);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String converteData(Date data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(data);
		return d;
	}
	
	public ClimaEntidade converteDtoParaEntidade(ResultadoClimaDTO resultado,String cidade){
		ClimaEntidade entidade = new ClimaEntidade();
		entidade.setData(converteData(resultado.getData()));
		entidade.setHumidity(resultado.getHumidity());
		entidade.setIcon(resultado.getIcon());
		entidade.setMain(resultado.getMain());
		entidade.setPressure(resultado.getPressure());
		entidade.setTempMax(resultado.getTempMax());
		entidade.setTempMin(resultado.getTempMin());
		entidade.setWind(resultado.getWind());
		entidade.setCidade(cidade);
		return entidade;
	}
	
	public ResultadoClimaDTO converteEntidadeParaDto(ClimaEntidade entidade){
		ResultadoClimaDTO r = new ResultadoClimaDTO();
		r.setData(converteData(entidade.getData()));
		r.setHumidity(entidade.getHumidity());
		r.setIcon(entidade.getIcon());
		r.setMain(entidade.getMain());
		r.setPressure(entidade.getPressure());
		r.setTempMax(entidade.getTempMax());
		r.setTempMin(entidade.getTempMin());
		r.setWind(entidade.getWind());
		r.setCidade(entidade.getCidade());
		return r;
	}
	
	public boolean isDataECidadeExistente(String cidade,String date){
		Date d = converteData(date);
		return repo.isNoBanco(cidade,d);
	}
}
