package com.example.fromzerotohero.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.fromzerotohero.DTO.ResultadoClimaDTO;
import com.example.fromzerotohero.service.ClimaService;

@RestController
@RequestMapping(path = "/clima")
public class ClimaApi {

	@Autowired
	ClimaService service;

	@RequestMapping(path = "/{cidade}", method = RequestMethod.GET)
	public List<ResultadoClimaDTO> getClima(@PathVariable("cidade") String cidade){
		List<ResultadoClimaDTO> tempo = service.retornaClima(cidade);
		return tempo;
	}

}
