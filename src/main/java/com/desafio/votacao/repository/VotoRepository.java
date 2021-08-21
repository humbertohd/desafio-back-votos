package com.desafio.votacao.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.desafio.votacao.dto.ResultadoDTO;
import com.desafio.votacao.dto.VotoFiltroDTO;
import com.desafio.votacao.model.Pauta;
import com.desafio.votacao.model.Voto;

@Repository
public class VotoRepository {

	private final EntityManager em;

	/**
	 * CONSTRUTOR
	 * 
	 * @param em
	 */
	public VotoRepository(EntityManager em) {
		this.em = em;
	}

	/**
	 * SALVA A OPCAO DE VOTO DO USUARIO
	 * 
	 * @param voto
	 * @throws Exception
	 */
	public void salvarVoto(VotoFiltroDTO voto) throws RuntimeException {
		try {
			// UTILIZANDO QUERY NATIVA
			StringBuilder query = new StringBuilder();
			query.append("insert into votacao.voto (cod_pauta, cpf, data, opcao) ");
			query.append("values (?, ?, ?, ?)");

			var sql = em.createNativeQuery(query.toString());
			sql.setParameter(1, voto.getCod_pauta());
			sql.setParameter(2, voto.getCpf());
			sql.setParameter(3, new Date());
			sql.setParameter(4, voto.getVoto().booleanValue());
			sql.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar o voto informado.", e);
		}
	}

	/**
	 * RECUPERA O RESULTADO DA VOTACAO DE DETERMINADA PAUTA
	 * 
	 * @param cod_pauta
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ResultadoDTO> obterResultadoVotacao(Long cod_pauta) throws RuntimeException {
		try {
			// UTILIZANDO QUERY NATIVA
			StringBuilder query = new StringBuilder();
			query.append("select v.opcao, count(*) as quantidade from voto as v ");
			query.append("group by v.opcao");

			var sql = em.createNativeQuery(query.toString());
			return (List<ResultadoDTO>) sql.getResultList().stream().map((item) -> ResultadoDTO.converter(item))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao obter o resultado da votacao.");
		}
	}

	/**
	 * VALIDA SE A PAUTA EM QUESTAO, AINDA ESTA EM VIGOR
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean validarVigenciaPauta(Long id) throws RuntimeException {
		try {
			// UTILIZANDO QUERY NATIVA
			StringBuilder query = new StringBuilder();
			query.append("select p.* from votacao.pauta as p ");
			query.append("where p.cod_pauta = :id ");
			query.append("and p.dataFim > :data");

			var sql = em.createNativeQuery(query.toString(), Pauta.class);
			sql.setParameter("id", id);
			sql.setParameter("data", new Date());

			var pauta = sql.getResultList();

			return (!pauta.isEmpty());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao validar vigencia da pauta.");
		}
	}

	/**
	 * VERIFICA SE O CPF INFORMADO JA VOTOU NA PAUTA
	 * 
	 * @param pauta
	 * @param cpf
	 * @return
	 * @throws Exception
	 */
	public Boolean verificaVotoExistente(Long pauta, String cpf) throws RuntimeException {
		try {
			// UTILIZANDO QUERY NATIVA
			StringBuilder query = new StringBuilder();
			query.append("select p.* from votacao.voto as p ");
			query.append("where p.cod_pauta = :pauta ");
			query.append("and p.cpf = :cpf");

			var sql = em.createNativeQuery(query.toString(), Voto.class);
			sql.setParameter("pauta", pauta);
			sql.setParameter("cpf", cpf);

			var voto = sql.getResultList();

			return (!voto.isEmpty());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao verificar voto existente na pauta.");
		}
	}
}
