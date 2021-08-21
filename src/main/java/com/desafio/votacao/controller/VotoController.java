package com.desafio.votacao.controller;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.votacao.dto.ResultadoDTO;
import com.desafio.votacao.dto.VotoFiltroDTO;
import com.desafio.votacao.service.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoController {

	@Autowired
	private VotoService votoService;

	/**
	 * RECUPERA O RESULTADO DA VOTACAO DE UMA DETERMINADA PAUTA
	 * 
	 * @param codigoPauta
	 * @return
	 */
	@GetMapping("/resultado/{codigoPauta}")
	public ResponseEntity<Object> getResultadoVotacao(@PathParam("codigoPauta") Long codigoPauta) {
		try {
			List<ResultadoDTO> lista = votoService.obterResultadoVotacao(codigoPauta);
			return new ResponseEntity<Object>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * REGISTRA O VOTO DE UM USUARIO
	 * 
	 * @param item
	 * @return
	 */
	@PostMapping("")
	public ResponseEntity<?> adicionarVoto(@RequestBody VotoFiltroDTO item) {
		try {
			votoService.incluir(item);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
