package com.castroantonio.reservas.model;

import java.math.BigDecimal;

public class Quarto {
	private String numero;
	private String descricao;
	private BigDecimal preco;

	public Quarto(String numero) {
		this.numero = numero;
	}
	
	public String getNumero() {
		return numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}