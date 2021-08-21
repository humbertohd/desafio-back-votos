package com.desafio.votacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.votacao.dto.PautaDTO;
import com.desafio.votacao.dto.PautaFiltroDTO;
import com.desafio.votacao.service.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaService;

	@GetMapping("")
	public ResponseEntity<Object> findAll() {
		try {
			List<PautaDTO> listaPauta = pautaService.findAll();
			return new ResponseEntity<Object>(listaPauta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getId(@PathVariable("id") Long id) {
		try {
			PautaDTO pauta = pautaService.getId(id);
			return new ResponseEntity<Object>(pauta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> addPauta(@RequestBody PautaFiltroDTO item) {
		try {
			pautaService.incluir(item);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
