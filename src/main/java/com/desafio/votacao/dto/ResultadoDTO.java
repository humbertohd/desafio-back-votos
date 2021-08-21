package com.desafio.votacao.dto;

import java.lang.reflect.Array;

public class ResultadoDTO {

	private String opcao;
	private Long quantidade;

	public static ResultadoDTO converter(Object item) {
		var vo = new ResultadoDTO();
		if ((boolean) Array.get(item, 0)) {
			vo.setOpcao("Sim");	
		} else {
			vo.setOpcao("Nao");
		}
		vo.setQuantidade(Long.valueOf(Array.get(item, 1).toString()));
		return vo;
	}

	/**
	 * @return the opcao
	 */
	public String getOpcao() {
		return opcao;
	}

	/**
	 * @param opcao the opcao to set
	 */
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	/**
	 * @return the quantidade
	 */
	public Long getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	
}
