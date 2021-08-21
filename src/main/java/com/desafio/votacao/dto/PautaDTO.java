package com.desafio.votacao.dto;

import java.util.Date;

import com.desafio.votacao.model.Pauta;

public class PautaDTO {

	private Long id;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	
	public static PautaDTO converter(Pauta pauta) {
		var vo = new PautaDTO();
		vo.setId(pauta.getId());
		vo.setDescricao(pauta.getDescricao());
		vo.setDataInicio(pauta.getDataInicio());
		vo.setDataFim(pauta.getDataFim());
		return vo;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}
	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}
	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
}
