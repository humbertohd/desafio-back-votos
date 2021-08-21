package com.desafio.votacao.dto;

public class VotoFiltroDTO {

	private Long cod_pauta;
	private String cpf;
	private Boolean voto;
	
	/**
	 * @return the cod_pauta
	 */
	public Long getCod_pauta() {
		return cod_pauta;
	}
	/**
	 * @param cod_pauta the cod_pauta to set
	 */
	public void setCod_pauta(Long cod_pauta) {
		this.cod_pauta = cod_pauta;
	}
	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}
	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	/**
	 * @return the voto
	 */
	public Boolean getVoto() {
		return voto;
	}
	/**
	 * @param voto the voto to set
	 */
	public void setVoto(Boolean voto) {
		this.voto = voto;
	}
}
