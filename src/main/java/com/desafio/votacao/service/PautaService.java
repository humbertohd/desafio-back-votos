package com.desafio.votacao.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.PautaDTO;
import com.desafio.votacao.dto.PautaFiltroDTO;
import com.desafio.votacao.model.Pauta;
import com.desafio.votacao.repository.PautaRepository;

@Service
public class PautaService {

	private final PautaRepository pautaRepository;

	/**
	 * CONSTRUTOR
	 * @param pautaRepository
	 */
	public PautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	/**
	 * RECUPERA TODAS AS PAUTAS CADASTRADAS
	 * @return
	 */
	public List<PautaDTO> findAll() {
		try {
			var pautas = pautaRepository.findAll();
			return pautas.stream().map((item) -> PautaDTO.converter(item)).collect(Collectors.toList());
			// return pautas.stream().map(PautaVO::converter).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar pautas");
		}
	}

	/**
	 * RECUPERA OS DADOS DE PAUTA ESPECIFICA
	 * @param id
	 * @return
	 */
	public PautaDTO getId(Long id) {
		try {
			var pauta = pautaRepository.getById(id);
			return PautaDTO.converter(pauta);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar pauta "+ id.toString());
		}
	}

	/**
	 * GRAVA NOVA PAUTA
	 * @param item
	 */
	public void incluir(PautaFiltroDTO item) {
		// TODOS OS CAMPOS DEVEM SER INFORMADOS
		if (item.getDescricao() == null || item.getDataInicio() == null || item.getDataFim() == null) {
			throw new RuntimeException("Entrada de dados inválida.");
		}

		// VERIFICA SE DATA INICIO MAIOR QUE DATA FIM
		if (item.getDataInicio().after(item.getDataFim())) {
			throw new RuntimeException("Datas invalidas.");
		}
		// GRAVA A PAUTA
		try {
			var pauta = new Pauta();
			pauta.setDescricao(item.getDescricao());
			pauta.setDataInicio(item.getDataInicio());
			pauta.setDataFim(item.getDataFim());
			pautaRepository.save(pauta);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao incluir pauta");
		}
	}
}
