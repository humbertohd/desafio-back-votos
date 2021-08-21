package com.desafio.votacao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "voto")
public class Voto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_VOTO")
	private Long id;

	@Column(name = "COD_PAUTA", nullable = false, unique = true)
	private Long codPauta;

	@Column(name = "CPF", nullable = false, unique = true)
	private String cpf;

	@Column(name = "OPCAO", nullable = false)
	private Boolean opcao;

	@Column(name = "DATA", nullable = false)
	private Date data;

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
	 * @return the codPauta
	 */
	public Long getCodPauta() {
		return codPauta;
	}

	/**
	 * @param codPauta the codPauta to set
	 */
	public void setCodPauta(Long codPauta) {
		this.codPauta = codPauta;
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
	 * @return the opcao
	 */
	public Boolean getOpcao() {
		return opcao;
	}

	/**
	 * @param opcao the opcao to set
	 */
	public void setOpcao(Boolean opcao) {
		this.opcao = opcao;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

}
