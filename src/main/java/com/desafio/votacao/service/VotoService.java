package com.desafio.votacao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.desafio.votacao.dto.CpfDTO;
import com.desafio.votacao.dto.ResultadoDTO;
import com.desafio.votacao.dto.VotoFiltroDTO;
import com.desafio.votacao.repository.VotoRepository;

import reactor.core.publisher.Mono;

@Service
public class VotoService {

	private static final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";

	private final VotoRepository repository;
	private final WebClient cpfApi;

	public VotoService(VotoRepository repository, WebClient cpfApi) {
		this.repository = repository;
		this.cpfApi = cpfApi;
	}

	/**
	 * RECUPERA O RESULTADO DA VOTACAO DE DETERMINADA PAUTA
	 * 
	 * @param codPauta
	 * @return
	 */
	public List<ResultadoDTO> obterResultadoVotacao(Long codPauta) {
		try {
			return repository.obterResultadoVotacao(codPauta);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao recuperar votos da pauta " + codPauta.toString());
		}
	}

	/**
	 * GRAVA O VOTO DO USUARIO
	 * 
	 * @param item
	 */
	@Transactional
	public void incluir(VotoFiltroDTO item) throws RuntimeException {
		// VERIFICA SE OS PARAMETROS FORAM INFORMADOS
		if (item.getCod_pauta() == null || item.getCpf() == null || item.getVoto() == null) {
			throw new RuntimeException("Entrada de dados inválida.");
		}

		// VERIFICA SE A PAUTA AINDA ESTÁ DISPONIVEL
		if (!repository.validarVigenciaPauta(item.getCod_pauta())) {
			throw new RuntimeException("Pauta inválida ou expirada.");
		}

		try {
			// VALIDA CPF
			Mono<CpfDTO> monoCpf = this.validarCpfApi(item.getCpf());

			// VERIFICA SE O CPF JA VOTOU NA PAUTA INFORMADA
			Boolean votoExistente = repository.verificaVotoExistente(item.getCod_pauta(), item.getCpf());

			// ESPERA O PROCESSAMENTO DA API
			CpfDTO cpfDTO = monoCpf.block();

			if (cpfDTO.getStatus().equals(UNABLE_TO_VOTE)) {
				throw new RuntimeException("CPF nao autorizado para votacao.");
			}

			if (votoExistente) {
				throw new RuntimeException("CPF informado ja realizou o voto nesta pauta.");
			}

			// GRAVA NO VOTO
			repository.salvarVoto(item);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private Mono<CpfDTO> validarCpfApi(String cpf) {
		// REALIZA A CONSULTA NA API DE CPF
		return this.cpfApi.method(HttpMethod.GET).uri("/{cpf}", cpf).retrieve()
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
						response -> Mono.error(new RuntimeException("CPF invalido")))
				.bodyToMono(CpfDTO.class);
	}
}
