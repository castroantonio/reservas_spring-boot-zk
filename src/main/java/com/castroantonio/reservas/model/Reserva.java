package com.castroantonio.reservas.model;

import java.util.Date;

import com.castroantonio.reservas.util.Hash;

public class Reserva {

	private Cliente cliente;
	private Quarto quarto;
	private Date inicio;
	private Date fim;
	private String protocolo;

	public Reserva(Cliente cliente, Quarto quarto, Date inicio, Date fim) {
		this.cliente = cliente;
		this.quarto = quarto;
		this.inicio = inicio;
		this.fim = fim;
		protocolo = Hash.gerarHash(cliente.getNome() + quarto.getNumero() + inicio + fim);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getFim() {
		return fim;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public String getProtocolo() {
		return protocolo;
	}
}